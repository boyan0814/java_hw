

load("nashorn:mozilla_compat.js");

importPackage(java.lang);
importPackage(java.io);
importPackage(java.net);
Object.prototype.toString = showObject;

var client = {
    serverName:"localhost",//"192.168.0.199",
    serverPort:1234,
    connect:function() {
        this.socket = new Socket( InetAddress.getByName(this.serverName), this.serverPort);
        this.output = new ObjectOutputStream(this.socket.getOutputStream() );
        this.output.flush();
        this.input = new ObjectInputStream(this.socket.getInputStream() );
    },
    listen:function() {
        var bytes = new java.lang.reflect.Array.newInstance(Byte.TYPE, 4096);
        while(true) {
           var numRead = this.input.read(bytes);
           if(numRead > 0) {
               System.out.write(bytes, 0, numRead);
               print("server:");
           }
        }
    },
    thread:new Thread(new Runnable() {
        run:function() {
            client.listen();
        }
    }),
    process:function() {
        var scanner = new java.util.Scanner(System["in"]);
        // var  message = this.input.readObject();
        // println(message ); // server ready
        print("server:");
        do {
           var command = scanner.nextLine();
           this.output.writeObject(command);
           this.output.flush();
           message = this.input.readObject();
           if(message <= 0) continue; //println(message)
        } while(command != "quit");
        this.thread.stop();
    },
    close:function() {
        try {
            this.output.close();
            this.input.close();
            this.socket.close();
        }
        catch (x) {
            println(x);
        }
    }
}

main();
function main()
{
    client.connect();
    // client.thread.start();
    client.process();
    client.close();
}
function showObject() {
    var s = "";
    for(var i in this) {
        if(typeof(this[i]) == 'function') continue;
        if(s != "") s += ",";
        if(typeof(this[i]) == 'object') s += i + ":"+ this[i].toString();
        else s += i+ ":" + this[i];
    }
    return (s == "") ? s : ("(" + s + ")" );
}
