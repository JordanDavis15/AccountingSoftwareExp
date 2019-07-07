@ECHO off
SET tempPlace="C:\temp\AccountingSoftwareExp"
SET destPlace=C:\Users\%USERNAME%\Documents\AccountingSoftwareExp

git clone https://github.com/JordanDavis15/AccountingSoftwareExp.git %tempPlace%

xcopy "%tempPlace%\build" "%destPlace%" /s /y
IF EXIST "%destPlace%\accountingData.txt" (
	ECHO data already exists...will not overwrite
)ELSE (
	xcopy "%tempPlace%\accountingData.txt" "%destPlace%" /s
)
RMDIR /Q/S %tempPlace%

rem creates run.bat
cd %destPlace%
ECHO cd "%destPlace%" > Run.bat
ECHO java -cp "%destPlace%\classes accounting_software_view.AccountingSoftwareExp" >> Run.bat

rem this adds a Desktop shortcut
ECHO $WshShell = New-Object -comObject WScript.Shell > makeShortcut.ps1
ECHO $Shortcut = $WshShell.CreateShortcut("$Home\Desktop\EasyAccounting.lnk") >> makeShortcut.ps1
ECHO $Shortcut.TargetPath ="%destPlace%\run.bat" >> makeShortcut.ps1
ECHO $Shortcut.Save() >> makeShortcut.ps1
PowerShell.exe -NoProfile -ExecutionPolicy Bypass -Command "& './makeShortcut.ps1'"
del makeShortcut.ps1
ECHO A shortcut has been placed on your desktop

PAUSE