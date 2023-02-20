# workflow-engine-spike

## APIs to test workflow
### Apply Leave
Apply leave. Here userId is an id of the user trying to apply leave.
/user/{userId}/applyLeave

### Leaves Pending Approval
Get list of leaves pending for approval with user having userId
/user/{userId}/leavesPendingApproval

### Approve leave step
Approve a leave request

/user/{userId}/approveLeaveStep/{leaveId}

here userId is the id of the approver user
leaveId is the leave request id
