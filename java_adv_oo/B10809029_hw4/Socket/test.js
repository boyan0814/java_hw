
importPackage(java.lang);

//println(this.eval)
eval("a=1")
new Thread(new Runnable() {
    run:function() {
        println("run...")
        eval("dir('.');")
    }

}).start();


