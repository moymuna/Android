# Implementation Plan - Advance Salary Feature

Add the "Advance Salary" feature to the HRM Application, allowing employees to request salary advances directly from the app.

## Proposed Changes

### 1. Data Models & API Integration
#### [NEW] [AdvanceRequest.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/model/request/AdvanceRequest.java)
- Fields: `amount`, `requestDate`, `requiredByDate`, `installments`, `reason`, `employeeId`.

#### [NEW] [AdvanceResponse.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/model/response/AdvanceResponse.java)
- Fields: `id`, `amount`, `requestDate`, `requiredByDate`, `installments`, `monthlyDeduction`, `recoveredAmount`, `outstandingAmount`, `reason`, `status`, `employeeId`, `employeeName`.

#### [MODIFY] [ApiService.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/api/ApiService.java)
- Add `@POST("api/advances/save")` for `saveAdvance`.
- Add `@GET("api/advances/employee/{employeeId}")` for `getAdvancesByEmployee`.

### 2. Repository
#### [NEW] [AdvanceRepository.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/Repository/AdvanceRepository.java)
- Handles API calls for requesting and fetching advance salary records.

### 3. Dashboard Integration
#### [MODIFY] [activity_employee_dashboard.xml](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/res/layout/activity_employee_dashboard.xml)
- Add a new button for "Advance Salary" in the Quick Actions section.

#### [MODIFY] [EmployeeDashboard.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/EmployeeDashboard.java)
- Initialize the new button and set an `OnClickListener` to open `AdvanceSalaryActivity`.

### 4. Advance Salary UI & Logic
#### [MODIFY] [activity_advance_salary.xml](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/res/layout/activity_advance_salary.xml)
- Create a form for requesting an advance (Amount, Required Date, Installments, Reason).
- Include a list/RecyclerView to show previous advance requests and their status.

#### [MODIFY] [AdvanceSalaryActivity.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/AdvanceSalaryActivity.java)
- Implement form validation and submission logic.
- Fetch and display the employee's advance salary history.

## Verification Plan

### Automated Tests
- Run `gradle_build app:assembleDebug` to ensure no compilation errors.

### Manual Verification
- Open the app, navigate to the dashboard, and click "Advance Salary".
- Submit a test request and verify it calls the backend API (via Logcat or server logs).
- Check if the history list updates with the new request.
