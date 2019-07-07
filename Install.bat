@echo off 

SET tempPlace=C:\temp
SET destPlace=C:\Users\%USERNAME%\Documents

xcopy build "%destPlace%\AccountingSoftwareExp" /s
xcopy accountingData.txt "%destPlace%\AccountingSoftwareExp" /s

rem changes directory to one stored in destPlace
cd "%destPlace%\AccountingSoftwareExp"
ECHO cd "%destPlace%\AccountingSoftwareExp" > Run.bat
ECHO java -cp %destPlace%\AccountingSoftwareExp\classes accounting_software_view.AccountingSoftwareExp >> Run.bat

rem this adds a Desktop shortcut
ECHO $WshShell = New-Object -comObject WScript.Shell > makeShortcut.ps1
ECHO $Shortcut = $WshShell.CreateShortcut("$Home\Desktop\EasyAccounting.lnk") >> makeShortcut.ps1
ECHO $Shortcut.TargetPath = "%destPlace%\AccountingSoftwareExp\run.bat" >> makeShortcut.ps1
ECHO $Shortcut.Save() >> makeShortcut.ps1
PowerShell.exe -NoProfile -ExecutionPolicy Bypass -Command "& './makeShortcut.ps1'"
del makeShortcut.ps1

ECHO A shortcut has been placed on your desktop
PAUSE
