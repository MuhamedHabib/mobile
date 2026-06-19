# CodNamePI — Mobile Training-Resource Manager

A cross-platform Codename One (Java) mobile app that manages "Formations" (training resources) by consuming a JSON REST API from a Symfony backend.

![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Codename One](https://img.shields.io/badge/Codename%20One-2C3E50?style=for-the-badge&logo=java&logoColor=white)
![Apache Ant](https://img.shields.io/badge/Apache%20Ant-A81C7D?style=for-the-badge&logo=apacheant&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-000000?style=for-the-badge&logo=apple&logoColor=white)

## Overview

CodNamePI is a mobile client built with [Codename One](https://www.codenameone.com/) — a write-once-run-anywhere framework that compiles Java source into native Android and iOS apps. The app provides full CRUD management of *Formations* (training / learning resources, e.g. videos and books) and talks to a separate Symfony web application over HTTP, exchanging data as JSON.

It was developed as an academic / learning project (the GUI labels mix French and English) and demonstrates the classic three-layer client pattern: entity model, network service, and form-based UI.

## Features

Grounded in the current source:

- **Home screen** (`HomeForm`) — entry menu to navigate to the available actions.
- **Add a Formation** (`AddFormationForm`) — a form with fields for *Libelle* (label), *Description*, *Type*, and *Image*, with client-side validation that all fields are filled before submitting.
- **List Formations** (`ListFormationForm`) — fetches all formations from the backend and renders them in a grid, with a success / error dialog for each operation.
- **Edit a Formation** — inline edit form ("Modifier") that updates an existing record by id.
- **Delete a Formation** — removes a record by id ("Supprimer") and refreshes the list.
- **Search by type** — a text field + "Recherche" action that reloads formations and shows a filtered view.
- **Networking & error handling** — a global network-error listener shows a connection-error dialog, and crash-protection logging is enabled in the app entry point.

> Note: a "files" feature (add / list files) is sketched in the home menu but is currently commented out and not implemented.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java (language level 8) |
| Framework | Codename One (cross-platform mobile, native Android + iOS) |
| UI | Codename One UI toolkit (`Form`, `Container`, `BoxLayout`, `GridLayout`, `Toolbar`, Material icons) |
| Networking | `ConnectionRequest` + `NetworkManager` |
| Data format | JSON, parsed with Codename One's `JSONParser` |
| Build system | Apache Ant (`build.xml`) + Codename One build client |
| Backend (external) | Symfony web app exposing JSON endpoints |

### Backend endpoints consumed

The service layer (`ServiceFormation`) calls the following routes on the backend base URL:

| Action | Endpoint |
|--------|----------|
| List all | `GET /all` |
| Add | `GET /addJSON?libelle=&description=&type=&image=` |
| Update | `GET /updateJSON?id=&libelle=&description=&type=&image=` |
| Delete | `GET /deleteFormationJSON/{id}` |

The base URL is configured in `utils/Statics.java` and defaults to a local development server (`http://127.0.0.1:8000`). Point it at your own Symfony instance to run end-to-end.

## Project Structure

```
mobile/
├── src/
│   └── com/myApp/CodNamePI/
│       ├── MyApplication.java          # App lifecycle entry point (init/start/stop)
│       ├── entities/
│       │   └── Myformation.java        # Formation model (id, libelle, description, type, image, date)
│       ├── services/
│       │   └── ServiceFormation.java   # REST client: add / list / update / delete + JSON parsing
│       ├── gui/
│       │   ├── HomeForm.java           # Main menu
│       │   ├── AddFormationForm.java   # Create form
│       │   └── ListFormationForm.java  # List / search / edit / delete
│       └── utils/
│           └── Statics.java            # Backend base URL constant
├── build.xml                           # Ant build (Android / iOS targets via Codename One)
├── codenameone_settings.properties     # Codename One project configuration
├── Versions.properties                 # Codename One library versions
├── theme.res / icon.png                # App theme and launcher icon
└── lib/                                 # Codename One libraries
```

## Getting Started

This is a standard Codename One project. The recommended workflow uses the Codename One plugin and its cloud build service.

### Prerequisites

- JDK 8
- Apache Ant
- A Codename One account (free), and the Codename One plugin for your IDE (NetBeans / IntelliJ IDEA / Eclipse). This project was developed with IntelliJ IDEA.
- The companion Symfony backend running and reachable (see endpoints above).

### Run / build

1. Open the project in an IDE with the Codename One plugin installed.
2. Set the backend address in `src/com/myApp/CodNamePI/utils/Statics.java` (`BASE_URL`) to your running Symfony server.
3. Use the Codename One plugin's **Run** action to launch the app in the desktop simulator, or build for a device.

Device builds are produced through the Codename One build cloud via the Ant targets in `build.xml`, for example:

```bash
ant build-for-ios-device      # iOS device build
ant offline-builder-android   # Android build
```

(Cloud builds require Codename One credentials configured in the plugin.)

## Notes

- The UI text mixes French and English (`Ajouter Formation`, `Recherche`, `Add Formation`), reflecting its origins as a learning project.
- The default backend URL points at `localhost`; update it before deploying or testing on a real device.
- No signing keystores, certificates, or API secrets are included in this repository — those fields in `codenameone_settings.properties` are intentionally left blank and must be supplied for release builds.
- The companion Symfony web application is maintained separately and is required for the app to function end-to-end.

---
<p align="center">Built by <b>Mohamed Habib Khattat</b> — <a href="https://github.com/MuhamedHabib">GitHub (@MuhamedHabib)</a> · <a href="https://www.linkedin.com/in/mohamed-habib-khattat-2b206a173">LinkedIn</a></p>
