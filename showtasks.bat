call runcrud
if "%ERRORLEVELS%" == "0" goto runbrowser
echo.
echo runcrud has errors
goto fail

:runbrowser
call start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVELS%" == "0" goto end
echo.
echo could not launch web browser
goto fail

:fail
echo.
echo there were errors

:end
echo.
echo completed successfully