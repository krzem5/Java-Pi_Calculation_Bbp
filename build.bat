@echo off
cls
if exist build rmdir /s /q build
mkdir build
cd src
javac -d ../build com/krzem/pi_calculation_bbp/Main.java&&jar cvmf ../manifest.mf ../build/pi_calculation_bbp.jar -C ../build *&&goto run
cd ..
goto end
:run
cd ..
pushd "build"
for /D %%D in ("*") do (
	rd /S /Q "%%~D"
)
for %%F in ("*") do (
	if /I not "%%~nxF"=="pi_calculation_bbp.jar" del "%%~F"
)
popd
cls
java -jar build/pi_calculation_bbp.jar
:end
