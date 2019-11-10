@java -cp "%~dp0\H2\bin\h2-1.4.200.jar;%H2DRIVERS%;%CLASSPATH%" org.h2.tools.Console %*
@if errorlevel 1 pause