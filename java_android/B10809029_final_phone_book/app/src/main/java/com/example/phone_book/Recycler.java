package com.example.phone_book;

import android.Manifest;
import android.content.Context;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import android.os.Bundle;

import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_book.data.AppDataBase;
import com.example.phone_book.data.PhoneItemEntry;
import com.example.phone_book.utilities.NotificationUtils;

import java.util.List;

public class Recycler extends Fragment {

    RecyclerView mRecycleView;

    MainAdapter mAdapter;
    private List<PhoneItemEntry> mPhoneItemEntries;
    Button btn_add_click,btn_item_input;

    private AppDataBase mDb; //DataBase 物件
    PhoneItemEntry task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //checkPermission();
        mDb = AppDataBase.getInstance(getActivity().getApplicationContext());
    }

    public void checkPermission() {
        //check condition
        if (ActivityCompat.checkSelfPermission(getContext()
                , Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            //if not granted
            //request
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }else {
            getContactList();
            testNotification(getView());
        }
    }

    public void getContactList() {
        Context applicationContext = SecondActivity.getContextOfApplication();

        //intitialize uri
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //sort by ascending
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        //initialize cursor
        Cursor cursor = applicationContext.getContentResolver().query(
                uri,null,null,null,sort
        );

        //check condition
        if (cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts._ID
                ));

                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                ));

                //initialize phone uri
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";

                Cursor phoneCursor = applicationContext.getContentResolver().query(
                        uriPhone, null, selection, new String[]{id}, null
                );

                //check condition
                if (phoneCursor.moveToNext()) {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));

                    task = new PhoneItemEntry(name,number);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.taskDao().insertTask(task);
                        }
                    });
                    retrieveTasks();
                    phoneCursor.close();
                }
            }
            cursor.close();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview,container,false);

        mRecycleView = view.findViewById(R.id.recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new MainAdapter(getActivity());
        mRecycleView.setAdapter(mAdapter);

        btn_add_click = view.findViewById(R.id.btn_add);
        btn_item_input = view.findViewById(R.id.btn_input);

        btn_add_click.setOnClickListener(v -> {
            addPhone();
            testNotification(getView());
        });

        btn_item_input.setOnClickListener(v -> {
            checkPermission();
        });
        //return super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private void addPhone() {
        //PhoneItem model = new PhoneItem();
        EditText name = getActivity().findViewById(R.id.editName);
        EditText phone = getActivity().findViewById(R.id.editPhone);

        if(name.getText().length() == 0 || phone.getText().length() == 0){return;}

        String strName = name.getText().toString();
        String strPhone = phone.getText().toString();

        task = new PhoneItemEntry(strName,strPhone);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.taskDao().insertTask(task);
            }
        });

        retrieveTasks();
        name.getText().clear();
        phone.getText().clear();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<PhoneItemEntry> tasks = mDb.taskDao().loadAllTasks();
                // We will be able to simplify this once we learn more
                // about Android Architecture Components
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTasks(tasks);
                    }
                });
            }
        });
    }

    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

        private Context mContext;
        public MainAdapter(Context context) {
            this.mContext = context;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvName, tvPhone;
            Button del_btn,detail_btn;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.item_name);
                tvPhone = itemView.findViewById(R.id.item_phone);

                del_btn = itemView.findViewById(R.id.item_btn_del);
                detail_btn = itemView.findViewById(R.id.btn_detail);

                del_btn.setOnClickListener(v -> {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            int position = getAdapterPosition();
                            mDb.taskDao().deleteTask(mPhoneItemEntries.get(position));
                            retrieveTasks();
                        }
                    });
                });

                detail_btn.setOnClickListener(v -> {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            int position = getAdapterPosition();
                            PhoneItemEntry temp = mDb.taskDao().loadTaskById(mPhoneItemEntries.get(position).getId());
                            Bundle bundle = new RecyclerArgs.Builder().setName(temp.getGuest_name()).setPhone(temp.getGuest_phone()).build().toBundle();
                            Navigation.findNavController(v).navigate(R.id.nav_detail,bundle);
                        }
                    });
                });

            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_item, parent, false);

            return new ViewHolder(view);
        }

        //@SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            //contact
            PhoneItemEntry item = mPhoneItemEntries.get(position);

            String guestname = item.getGuest_name();
            String guestphone = item.getGuest_phone();

            //set
            holder.tvName.setText(guestname);
            holder.tvPhone.setText(guestphone);
        }

        @Override
        public int getItemCount() {
            if (mPhoneItemEntries == null) {
                return 0;
            }
            return mPhoneItemEntries.size();
        }
    }

    public List<PhoneItemEntry> getTasks() {
        return mPhoneItemEntries;
    }

    public void setTasks(List<PhoneItemEntry> taskEntries) { //設定進入後刷新變更
        mPhoneItemEntries = taskEntries;
        mAdapter.notifyDataSetChanged();
    }

    public void testNotification(View view) {
        NotificationUtils.remindUserBecauseCharging(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveTasks();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    return true;
                }
                return false;
            }
        });
    }
}
