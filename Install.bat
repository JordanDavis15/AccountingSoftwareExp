@echo off
SET /P inputname= Enter path for installation(ex. C:\Program Files): 
xcopy build "%inputname%\AccountingSoftwareExp" /s
xcopy accountingData.txt "%inputname%\AccountingSoftwareExp" /s

rem changes directory to one stored in inputname
cd "%inputname%\AccountingSoftwareExp"
echo cd "%inputname%\AccountingSoftwareExp" > Run.bat
echo java -cp %inputname%\AccountingSoftwareExp\classes accounting_software_view.AccountingSoftwareExp >> Run.bat

explorer %inputname%\AccountingSoftwareExp

echo.
echo ======= please execute the run.bat file in the window that just opened to run the program =======
echo.

rem this adds a Desktop shortcut
echo $WshShell = New-Object -comObject WScript.Shell > makeShortcut.ps1
echo $Shortcut = $WshShell.CreateShortcut("$Home\Desktop\EasyAccounting.lnk") >> makeShortcut.ps1
echo $Shortcut.TargetPath = "%inputname%\AccountingSoftwareExp\run.bat" >> makeShortcut.ps1
echo $Shortcut.Save() >> makeShortcut.ps1
PowerShell.exe -NoProfile -ExecutionPolicy Bypass -Command "& './makeShortcut.ps1'"
del makeShortcut.ps1

pause
