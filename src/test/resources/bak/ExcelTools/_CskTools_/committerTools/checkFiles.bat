@echo off
rem check filesob`

rem ψ`FbN
if "%1" == "" goto usage

rem t@C`FbNΐs
:exectest
if "%1" == "" goto end
if exist %1\nul (

cd %1

echo  [%1] start 
more src\site\apt\index.apt
pause
more pom.xml
echo  [%1] end  
pause

cd ..
) else echo [%time%] "%1" vWFNgͺΆέ΅άΉρ.
shift /1
goto exectest

rem USAGE
:usage

echo usage: checkFiles [productId] [productId] ...
echo.

goto end

rem IΉ
:end

