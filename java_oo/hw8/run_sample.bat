@echo on
@echo Clean "classes" folder
@del /q classes\*
@pause

@echo Change directory to "source", and use -d to compile
cd source
javac -d ../classes ntust/mis/oo/*.java -encoding utf-8
@pause

@echo Change directory to "classes", and package all classes in ntust into a JAR
cd ../classes
jar -cvf Delivery.jar ntust
@pause

@echo Change directory to "source". and compile DeliveryTest
cd ../source
javac -d ../classes ntust/mis/test/DeliveryTest.java -encoding utf-8
@pause

@echo Run DeliveryTest
cd ../classes
@echo Use classes/ntust/mis/oo classes
java ntust.mis.test.DeliveryTest
@pause

@echo Include Delivery.jar classes and run DeliveryTest
java -cp Delivery.jar; ntust.mis.test.DeliveryTest
@pause

@echo It fails if we delete ntust\mis\oo
@del /q ntust\mis\oo
java ntust.mis.test.DeliveryTest
@pause

@echo Include Delivery.jar classes, and it will be successful
java -cp Delivery.jar; ntust.mis.test.DeliveryTest
@pause

