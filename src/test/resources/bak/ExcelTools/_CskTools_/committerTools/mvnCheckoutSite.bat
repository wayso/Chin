@echo off
setlocal enabledelayedexpansion
rem maven deploy/testバッチ用環境設定

rem process_type test:mvn test deploy:mvn deploy deploy_ci:rel all deploy_notest deploy_ci_notest
set _PROCESS_TYPE=site
call setSchemaEnv

call svncheckout %*

shift /1

call mvntool_core %*

exit /b %_EXEC_RESULT%
endlocal
