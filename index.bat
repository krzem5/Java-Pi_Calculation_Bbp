echo off
echo NUL>_.class&&del /s /f /q *.class
cls
javac com/krzem/pi_calculation_bbp/Main.java&&java com/krzem/pi_calculation_bbp/Main
start /min cmd /c "echo NUL>_.class&&del /s /f /q *.class"