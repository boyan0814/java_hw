/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5;

/**
 *
 * @author boyan
 */
public class Student implements Listable {

	String key;
	int school;

	public Student(String key, int school) {
		this.key = key;
		this.school = school;
	}
	@Override
	public int listing() {
		int h = 0;
		for (int i = 0; i < key.length(); i++) {
			h += key.charAt(i);
		}
		return h;
	}	
	@Override
	public String toString() {
		return key + ": "+school;
	}

	@Override
	public boolean equals(Object o) {
		return key.equals(((Student)o).key);
	}
        
        @Override
        public String getKey() {
		return key;
	}
}
