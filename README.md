# 🔐 Cipher - SMS Spam Detection Android App

**Cipher** is a cybersecurity-focused Android application built to intelligently detect and manage spam SMS messages using machine learning. It combines both manual and automated approaches to classify messages as **safe** or **spam**, while offering real-time actions like blocking unwanted senders.

---

## 🚀 Features

### 📂 1. Inbox-Wide Spam Detection
- Automatically accesses and scans the device's full SMS inbox.
- On tapping any SMS, the app triggers real-time classification using the trained ML model.
- Displays an immediate **popup notification** indicating whether the message is **safe** or **spam**.
- If spam is detected, users are prompted with a **"Block Sender"** option for immediate control.

### ✍️ 2. Manual Message Check (Paste-to-Scan)
- Users can **manually input or paste SMS text** into the dashboard’s input field.
- The app processes the content through the backend model and displays the result instantly.
- Ideal for testing suspicious or unknown message content.

### 📋 3. Message Dashboard with Member Overview
- A central dashboard offers:
  - Access to both manual and inbox-wide scan features.
  - A clean interface for reviewing message results.
  - A **"Meet the Team"** section highlighting contributor roles and project credits.

---

## 🛠️ Built With

- **Language**: Java / Kotlin
- **IDE**: Android Studio
- **Architecture**: MVC
- **Machine Learning**: NLP-based text classification
- **Model Integration**: TensorFlow Lite or rule-based logic
- **Minimum SDK**: API 21 (Android 5.0+)

---

## 🧑‍💻 Team Contribution

This app was developed as part of a 4-member final-year academic project.

**My Role**: Backend Development & Machine Learning  
- Collected and preprocessed SMS datasets for spam classification.  
- Trained a text classification model using NLP techniques (tokenization, TF-IDF, etc.).  
- Integrated the trained model into the Android application using optimized backend logic.  
- Implemented message parsing, on-device prediction, and result display via notifications.

### 💡 Challenges Tackled
- Parsing real-world SMS data with high variability and formatting issues.
- Maintaining high classification accuracy across both short and long messages.
- Embedding an efficient and lightweight model suitable for real-time mobile processing.

---

## 📦 APK Download

> 📱 You can download the latest build from the [Releases](https://github.com/Taimeen/Cipher/releases) section.

---

## 📷 Screenshots



<h3 align="center">📱 App Screenshots</h3>

<p align="center">
  <img src="https://github.com/user-attachments/assets/b3651ea9-e00e-412c-96b9-bb3b294e6a0b" width="200" />
  <img src="https://github.com/user-attachments/assets/9ec7bdf9-9b00-4dab-8614-2ef327878159" width="200" />
  <img src="https://github.com/user-attachments/assets/d223b17f-11cf-4ca6-9f97-4f49a60a5898" width="200" />
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/0f9c47bb-3fc3-4579-954a-05da2b9df2fb" width="200" />
  <img src="https://github.com/user-attachments/assets/6d258677-3d6c-4e66-ba7c-2cd645acaa6c" width="200" />
  <img src="https://github.com/user-attachments/assets/06cf71ae-f35e-4fac-9551-bd74e171360d" width="200" />
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/1672ec14-10d5-48a7-a51f-47783cc55c78" width="200" />
</p>



---

## 🔒 Required Permissions

- `READ_SMS`: To access and scan received messages.
- `RECEIVE_SMS`: To detect incoming SMS in real-time.
- `INTERNET`: (Optional) If remote validation or analytics are used.

---

## 📂 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Taimeen/Cipher.git
