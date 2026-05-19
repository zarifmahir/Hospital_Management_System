# Hospital Management System

A comprehensive GUI-based desktop application developed using **JavaFX** for managing various hospital operations. This project was developed as part of the CSE 108 (Level 1, Term 2) coursework.

## Features

This system provides a wide array of administrative and healthcare features with designated roles for Admins, Doctors, Patients, and Staff.

### 👤 Role-Based Access Control
- Different portals for **Admin**, **Doctor**, **Patient**, and **Staff**.
- Secure Login and Registration pages tailored to each user type.

### 👑 Admin Panel
- **Dashboard:** Overview of hospital operations.
- **Manage Users:** Dedicated panels for managing Doctors, Patients, and Staff.
- **Appointments Management:** Schedule, review, and manage patient appointments.
- **Prescriptions Management:** View all overall prescriptions issued.

### 👨‍⚕️ Doctor Panel
- **Doctor Dashboard:** Overview of doctor's agenda and patient visits.
- **Add Prescriptions:** Create and manage patient prescriptions securely.
- **Real-time Chat:** Communicate with patients directly via a networked chat system.

### 🤕 Patient Panel
- **Patient Dashboard:** Overall view of the patient's medical summary.
- **Appointments:** Request and track doctor appointments.
- **View Prescriptions:** Safely view prescribed medications and doctor's notes.
- **Real-time Chat:** Built-in chat to directly speak with the doctor.

### 🛠 Technical Specifications
- **Framework:** JavaFX (Frontend and GUI)
- **Networking:** Built-in Network sockets for real-time chat between Doctors and Patients.
- **Storage:** Text-based flat-file database approach (`.txt` files for Admins, Doctors, Patients, Appointments, and Prescriptions).
- **Styling:** CSS styling for JavaFX (`style.css`, `buttonstyle.css`, etc.) for an aesthetic look.
- **Build Tool:** Maven

## Project Structure
```text
Hospital_Management_System/
├── pom.xml
└── src/
    └── main/
        ├── java/com/example/hospital_management_system/
        │   ├── admin_page/         # Admin functionalities
        │   ├── appointment_system/ # Appointment handling
        │   ├── doctor_page/        # Doctor functionalities
        │   ├── Networking/         # Real-time chat & network classes
        │   ├── patient_page/       # Patient functionalities
        │   ├── register_page/      # Registration & Login
        │   ├── Staff/              # Staff management
        │   └── Main.java           
        └── resources/
            ├── com/example/hospital_management_system/
            │   ├── admin_page/     # Admin FXMLs
            │   ├── doctor_page/    # Doctor FXMLs
            │   ├── patient_page/   # Patient FXMLs
            │   ├── register_page/  # Auth FXMLs
            │   └── ...
            ├── audio/              # Notification sounds
            ├── images/             # Icons & project images
            └── texts/              # Flat-file database (.txt) & CSS files
```

## Team Members
- [Zarif Mahir](https://github.com/zarifmahir)
- [Zarif Ishmam](https://github.com/ishmamzarif)

## How to Run
1. Make sure you have **Java** (JDK 17 or higher) and **Maven** installed.
2. Clone this repository.
3. Open a terminal and navigate to the root directory `Hospital_Management_System`.
4. **Important**: Before launching the Main application, you must run the server first so clients can connect locally or through the network.
   Run `ChatServer.java` found in `src/main/java/com/example/hospital_management_system/Networking/ChatServer.java` using your preferred IDE.
5. In another terminal, run the main project using Maven:
   ```bash
   mvn clean javafx:run
   ```
   *(Alternatively, run `Main.java` from your preferred IDE like IntelliJ IDEA or Eclipse)*

### 🌐 Cross-Device Networking
To establish networking (e.g., real-time chat) between two different devices on the same local network, the `serverAddress` variable in `Client.java` and `Main.java` must be updated to the IPv4 address of the host machine running the server. Otherwise, it should remain `127.0.0.1` for local testing on a single machine.

## License
This project is open-source and available under the [MIT License](LICENSE) (or you can use it freely for educational purposes).
