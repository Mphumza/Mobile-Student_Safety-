# 📱 Mphumza Student Safety Mobile App

> 🔐 Empowering schools and families with real-time, QR-based student safety tracking — built with **Java**, **XML**, **Firebase**, and 💡 future-ready AI ideas.

---

## 🚀 About the App

This mobile app is the **companion** to the [Mphumza Student Safety Web App](#), designed specifically for **students** to log in and interact with the system using **pre-registered parent credentials**.

### 🧠 Core Idea
Instead of registering separately, students can simply **log in** with the same Firebase-authenticated accounts created by their parents through the web app. They then access their personal **Student Dashboard** to scan QR codes for safety check-ins.

---

## 🎯 Core Functionality

| Feature                    | Status        | Description                                                                 |
|---------------------------|---------------|-----------------------------------------------------------------------------|
| 🔐 **Student Login**       | ✅ Complete   | Students log in using Firebase credentials created by their parents.       |
| 📲 **Student Dashboard**   | ✅ Complete   | Simple UI panel with one action: "Scan QR Code".                           |
| 📸 **QR Code Scanner**     | 🚧 Coming Soon | Will scan student-specific codes displayed in admin panel at school.      |
| ✉️ **Parent Notification** | 🧠 Planned     | Will send **SMS/Email alerts** when students enter/leave school premises. |
| 🤖 **AI Safety Tips**      | 🧠 Planned     | Future addition: AI-powered safety tips displayed to students in-app.     |

> 🔍 Currently, tapping the **Scan QR Code** button shows a friendly flash message:  
> **"QR Code Scanner coming soon!"**

---

## 🔄 System Flow Overview

### 🔗 Database
- Unified Firebase Realtime Database/Firestore
- Same database powers both **web** and **mobile** apps

### 👥 Roles
- **Parents** register on the **web app**
- **Students** log in on the **mobile app** using their parent's credentials
- **Admins** manage and display QR codes in the **web dashboard**

### 🏫 Usage Example

1. Student logs in using parent’s Firebase account
2. Sees a button: **"Scan QR Code"**
3. QR codes are displayed in the **admin panel** (web app)
4. Student scans their specific code on screen at school entry/exit
5. (Future) Parent is alerted of arrival/departure instantly via Email/SMS

---

## 🧱 Tech Stack

| Technology      | Usage                                       |
|-----------------|---------------------------------------------|
| 💻 Android Studio | Native mobile development environment       |
| 🔤 Java + XML     | Pure Android development (no Flutter, etc.) |
| 🔥 Firebase SDK  | Authentication + Database                   |
| 🧩 QR Code        | (Coming Soon) Barcode/QR Scanning support  |

---

## 📸 UI Preview
![Screenshot 2025-04-15 144635](https://github.com/user-attachments/assets/fbffd0f1-60ac-4da0-aa44-332cc535aa21)

![Screenshot 2025-04-15 173045](https://github.com/user-attachments/assets/d5582bdd-ca51-4cfe-beb0-598ca63b2854)

![Screenshot 2025-04-15 173105](https://github.com/user-attachments/assets/cbff1bce-9fa8-4fdb-a9e6-05404fb951b0)




