@ECHO off
SET tempPlace=C:\temp\AccountingSoftwareExp
SET destPlace=C:\Users\%USERNAME%\Documents\AccountingSoftwareExp

cd tempPlace
git clone https://github.com/JordanDavis15/AccountingSoftwareExp.git %tempPlace%

xcopy build "%destPlace%" /s /y
IF EXIST "%destPlace%\accountingData.txt" (
	ECHO data already exists...will not overwrite
)ELSE (
	xcopy accountingData.txt "%destPlace%" /s
)
RMDIR /Q/S %tempPlace%

rem this adds a Desktop shortcut
ECHO $WshShell = New-Object -comObject WScript.Shell > makeShortcut.ps1
ECHO $Shortcut = $WshShell.CreateShortcut("$Home\Desktop\EasyAccounting.lnk") >> makeShortcut.ps1
ECHO $Shortcut.TargetPath = "%destPlace%\AccountingSoftwareExp\run.bat" >> makeShortcut.ps1
ECHO $Shortcut.Save() >> makeShortcut.ps1
PowerShell.exe -NoProfile -ExecutionPolicy Bypass -Command "& './makeShortcut.ps1'"
del makeShortcut.ps1
ECHO A shortcut has been placed on your desktop

PAUSE