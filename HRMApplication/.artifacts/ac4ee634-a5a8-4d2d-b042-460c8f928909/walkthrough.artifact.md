# Walkthrough - Advance Salary Feature

I have successfully implemented the "Advance Salary" feature in the HRM Application. This feature allows employees to request salary advances directly from the app and track the status of their previous requests.

## Changes Made

### 1. Data Models & API Integration
- **[NEW]** [AdvanceRequest.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/model/request/AdvanceRequest.java): Request model containing amount, dates, installments, and reason.
- **[NEW]** [AdvanceResponse.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/model/response/AdvanceResponse.java): Response model including status, monthly deduction, and recovery tracking.
- **[MODIFY]** [ApiService.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/api/ApiService.java): Added endpoints for saving and fetching advance requests.

### 2. Repository
- **[NEW]** [AdvanceRepository.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/Repository/AdvanceRepository.java): Encapsulates API calls for the advance salary feature.

### 3. Dashboard Integration
- **[MODIFY]** [activity_employee_dashboard.xml](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/res/layout/activity_employee_dashboard.xml): Added an "Advance Salary" button in the quick actions section.
- **[MODIFY]** [EmployeeDashboard.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/EmployeeDashboard.java): Set up the click listener for the new button.

### 4. Advance Salary UI & Logic
- **[MODIFY]** [activity_advance_salary.xml](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/res/layout/activity_advance_salary.xml): Designed a dual-section UI with a request form and a history list.
- **[MODIFY]** [AdvanceSalaryActivity.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/AdvanceSalaryActivity.java): Implemented form validation, date picking, submission, and data loading logic.
- **[NEW]** [AdvanceAdapter.java](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/java/com/example/myaplication/adapter/AdvanceAdapter.java): RecyclerView adapter for displaying advance request history.
- **[NEW]** [item_advance.xml](file:///F:/JEE69 Course/Android/Android/HRMApplication/app/src/main/res/layout/item_advance.xml): Card-based item layout for the history list.

## Verification Results

### Automated Tests
- The project was successfully compiled with all new components integrated.

### Manual Verification
- Verified that the "Advance Salary" button appears on the dashboard and opens the correct activity.
- The request form includes a Material Date Picker for the "Required By Date".
- Form submission sends a POST request to the backend and updates the list upon success.
- Statuses (PENDING, APPROVED, REJECTED) are visually distinguished in the history list.
