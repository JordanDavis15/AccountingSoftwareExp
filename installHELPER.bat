@echo off
if not "%1"=="am_admin" (powershell start -verb runas '%0' am_admin & exit /b)

echo cd "C:\Users\%username%\Desktop" > Run.bat
echo java -cp c:\temp\AccountingSoftwareExp\classes accounting_software_view.AccountingSoftwareExp >> Run.bat