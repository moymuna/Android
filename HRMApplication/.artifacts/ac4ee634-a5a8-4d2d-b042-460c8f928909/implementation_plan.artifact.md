# Implementation Plan - Create Missing Icon Resources

Create the missing vector drawable resources required for the `EmployeeDashboard` and `NavigationView` menu.

## Proposed Changes

### [NEW] Drawable Resources

Create the following vector drawable files in `app/src/main/res/drawable/`:

- `ic_notification.xml`: Notification bell icon.
- `ic_person.xml`: User profile/person icon.
- `ic_home.xml`: Dashboard/Home icon.
- `ic_attendance.xml`: Calendar/Check icon for attendance.
- `ic_leave.xml`: Time off/Holiday icon for leaves.
- `ic_salary.xml`: Money/Finance icon for salary.
- `ic_payslip.xml`: Receipt/Document icon for payslips.
- `ic_documents.xml`: Folder/Files icon for documents.
- `ic_notice.xml`: Announcement/Notice board icon.
- `ic_holiday.xml`: Event/Holiday icon.
- `ic_project.xml`: Work/Assignment icon for projects.
- `ic_training.xml`: School/Learning icon for training.
- `ic_logout.xml`: Exit/Power icon for logout.
- `ic_menu.xml`: Hamburger menu icon (3 bars).

## Verification Plan

### Manual Verification
- Deploy the app and check the `EmployeeDashboard`.
- Open the Navigation Drawer and verify all menu icons are displayed correctly.
- Check the toolbar for the menu and notification icons.
- Verify the employee profile card displays the placeholder person icon.
