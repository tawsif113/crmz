# CRM System - Functional Requirements Specification (FRS)

**Core Features Only (Enhanced Prototype Version)**

# 1. Document Information

- **Project Name:** Core CRM System
- **Document Type:** Functional Requirements Specification
- **Version:** 1.1
- **Date:** August 2025

## 2. System Overview

A streamlined Customer Relationship Management system focusing on essential contact management, lead tracking, and basic sales pipeline functionality. This enhanced prototype includes additional usability and security features while maintaining simplicity.

# 3. Core Functional Requirements

### 3.1 Contact Management

#### 3.1.1 Contact Creation and Storage

- System SHALL allow users to create new contacts.
- System SHALL store contact information persistently.
- System SHALL prevent duplicate contacts based on email address.
- **System SHALL allow users to import contacts from CSV files.**
- **System SHALL allow users to export contacts to CSV files.**

**Required Contact Fields:**

- First Name (Required)
- Last Name (Required)
- Email Address (Required, Unique)
- Phone Number (Optional)
- Company Name (Optional)
- Job Title (Optional)
- Notes (Optional, Text area)
- Created Date (Auto-generated)
- Last Modified (Auto-generated)

#### 3.1.2 Contact Management Operations

- System SHALL allow users to view contact details.
- System SHALL allow users to edit existing contacts.
- System SHALL allow users to delete contacts.
- System SHALL allow users to search contacts by name, email, or company.

### 3.2 Lead Management

#### 3.2.1 Lead Tracking

- System SHALL allow conversion of contacts to leads.
- System SHALL track lead status progression.
- System SHALL assign monetary value to leads.
- **System SHALL allow users to set follow-up dates for leads.**
- **System SHALL notify users of upcoming follow-up dates.**

**Required Lead Fields:**

- Lead ID (Auto-generated)
- Contact ID (Reference to contact)
- Lead Source (Dropdown: Website, Referral, Cold Call, Email, Other)
- Lead Status (Dropdown: New, Qualified, Proposal, Negotiation, Closed Won, Closed Lost)
- Estimated Value (Currency field)
- Expected Close Date (Date field)
- Assigned Sales Rep (User reference)
- Created Date (Auto-generated)
- Last Activity Date (Auto-updated)
- **Follow-up Date (Optional, Date field)**

#### 3.2.2 Lead Operations

- System SHALL allow users to create new leads.
- System SHALL allow users to update lead status.
- System SHALL allow users to assign leads to sales representatives.
- System SHALL allow filtering leads by status and assigned rep.

### 3.3 Activity Tracking

#### 3.3.1 Activity Logging

- System SHALL log all interactions with contacts/leads.
- System SHALL allow manual activity entry.
- System SHALL timestamp all activities.

**Required Activity Fields:**

- Activity ID (Auto-generated)
- Related Contact/Lead ID (Reference)
- Activity Type (Dropdown: Call, Email, Meeting, Note)
- Subject (Required, Text)
- Description (Optional, Text area)
- Activity Date/Time (Required)
- User ID (Auto-assigned to current user)
- **Status (Dropdown: Planned, In Progress, Completed)**

#### 3.3.2 Activity Operations

- System SHALL allow users to add new activities.
- System SHALL allow users to view activity history per contact/lead.
- System SHALL display activities in chronological order.

### 3.4 User Management

#### 3.4.1 Basic User Authentication

- System SHALL require user login.
- System SHALL maintain user sessions.
- System SHALL allow password changes.
- **System SHALL allow users to update their profile information (except role and status).**

**Required User Fields:**

- User ID (Auto-generated)
- Username (Required, Unique)
- Email (Required, Unique)
- First Name (Required)
- Last Name (Required)
- Role (Dropdown: Admin, Sales Rep, Viewer)
- Status (Active/Inactive)
- Created Date (Auto-generated)

### 3.5 Basic Reporting

#### 3.5.1 Essential Reports

- System SHALL generate leads by status report.
- System SHALL generate leads by sales rep report.
- System SHALL generate activity summary report.
- **System SHALL allow users to export reports to PDF or Excel format.**

**Report Requirements:**

- Reports SHALL be viewable on screen.
- Reports SHALL include basic filtering (date range, user, status).
- Reports SHALL show record counts and totals where applicable.

## 4. User Interface Requirements

### 4.1 Navigation

- System SHALL provide main navigation menu.
- System SHALL include: Dashboard, Contacts, Leads, Activities, Reports sections.

### 4.2 Dashboard

- System SHALL display summary statistics:
  - Total contacts count
  - Total leads count
  - Leads by status (pie chart or summary)
  - Recent activities (last 10)
- **System SHALL provide a quick search bar on the dashboard for contacts and leads.**

### 4.3 List Views

- System SHALL provide searchable list views for contacts and leads.
- System SHALL support sorting by key columns.
- System SHALL provide pagination for large datasets.
- **System SHALL allow users to choose the number of records per page (e.g., 10, 25, 50, 100).**

### 4.4 Forms

- System SHALL provide forms for creating/editing contacts, leads, and activities.
- System SHALL validate required fields.
- System SHALL display clear error messages.

## 5. Data Validation Rules

### 5.1 Contact Validation

- Email format must be valid.
- Phone numbers should accept various formats.
- Names cannot be empty or only whitespace.

### 5.2 Lead Validation

- Estimated value must be positive number.
- Expected close date cannot be in the past.
- Lead must be associated with existing contact.

### 5.3 Activity Validation

- Activity date cannot be more than 1 year in the future.
- Subject field cannot be empty.
- Activity must be linked to existing contact or lead.

## 6. System Behavior

### 6.1 Search Functionality

- System SHALL provide case-insensitive search.
- System SHALL search across multiple fields simultaneously.
- System SHALL return partial matches.
- **System SHALL provide auto-suggestions in search fields based on existing data.**

### 6.2 Data Relationships

- Deleting a contact SHALL require confirmation if leads exist.
- Deleting a lead SHALL preserve associated activities.
- System SHALL maintain referential integrity.
- **System SHALL prevent deletion of users who have assigned leads or activities, or provide a warning and require confirmation.**

### 6.3 Audit Trail

- System SHALL track who created each record.
- System SHALL track when records were last modified.
- System SHALL track who modified each record.

## 7. Performance Requirements

- System SHALL load pages within 3 seconds under normal load.
- System SHALL support up to 10,000 contacts without performance degradation.
- System SHALL support concurrent access by up to 50 users.
- **System SHALL perform searches within 2 seconds for up to 10,000 records.**
- **System SHALL generate reports within 5 seconds for up to 1,000 records.**

## 8. Security Requirements

- System SHALL require authentication for all access.
- System SHALL use secure password storage (hashed).
- System SHALL log all user actions.
- System SHALL provide role-based access control.
- **System SHALL automatically log out users after 30 minutes of inactivity.**

## 9. Out of Scope (Future Phases)

- Email integration
- Calendar integration
- Advanced reporting and analytics
- Mobile application
- API integrations
- Document management
- Marketing automation
- Advanced workflow management
- Multi-currency support
- Custom fields

## 10. Acceptance Criteria Summary

The system is considered complete when:

- All contact management operations function correctly, including import/export.
- Lead pipeline management is operational, including follow-up notifications.
- Activity logging works for all record types with status tracking.
- Basic reports generate accurate data and can be exported.
- User authentication, authorization, and profile updates work properly.
- All data validation rules are enforced.
- Search functionality, including auto-suggestions, works across all modules.
-  Performance and security requirements are met.
