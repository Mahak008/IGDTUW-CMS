## Introduction
The University Complaint Management System is a Java-based application designed to streamline the process of managing complaints within a university. This system allows students to lodge complaints and administrators to manage and resolve these complaints efficiently.

## Features
<ul>
  <li>Student Module: Students can register, login, and submit complaints.</li>
  <li>Admin Module: Administrators can view, categorize, and resolve complaints.</li>
  <li>Complaint Tracking: Track the status of complaints from submission to resolution.</li>
  <li>Notification System: Email notifications to keep students updated on their complaint status.</li>
</ul>

## Technologies Used
<ul>
  <li>Java: The core programming language used.</li>
  <li>MySQL: Database management system for storing complaint data.</li>
  <li>Servlets and JSP: For handling HTTP requests and responses.</li>
  <li>HTML/CSS: For front-end design.</li>
  <li>Maven: For project build and dependency management.</li>
</ul>

## Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/IGDTUW-CMS.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd IGDTUW-CMS.git
    ```

3. **Set up the database:**
    - Import the provided SQL file (`database.sql`) into your MySQL server. You can do this using a tool like phpMyAdmin or via the MySQL command line:

        ```sql
        mysql -u yourusername -p yourpassword < path/to/database.sql
        ```

    - Update the database configuration in the `src/main/resources/database.properties` file with your MySQL server details:

        ```properties
        db.url=jdbc:mysql://localhost:3306/your_database_name
        db.username=yourusername
        db.password=yourpassword
        ```

4. **Build the project using Maven:**

    ```bash
    mvn clean install
    ```

5. **Deploy the application on a servlet container:**
    - If you're using Apache Tomcat, follow these steps:
        - Copy the generated WAR file from the `target` directory to the Tomcat `webapps` directory.
        - Start or restart the Tomcat server.

        Alternatively, you can use the Tomcat Maven Plugin to deploy directly:

        ```bash
        mvn tomcat7:deploy
        ```
        
6. **Access the application:**
    - Open your web browser and navigate to `http://localhost:8080/your-app-context`.
    - Students can register, log in, and submit complaints.
    - Administrators can log in to the admin panel to manage complaints.
  
## Usage
<ul>
  <li>Students: Register an account, login, and submit complaints through the web interface.</li>
  <li>Administrators: Login to the admin panel, view complaints, and update their status.</li>
</ul>

## Project Contributors

<table>
  <tr>
    <td  align="center">
      <img src="https://github.com/Mahak008/IGDTUW-CMS/assets/88129955/1e0d21ff-b940-49f0-a1c9-3eeef9729856" width=150px height=150px />
      <h4>Anisha</h4>
        <a href="https://github.com/Miss-Anisha">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Github-Dark.svg" alt="Miss-Anisha" height="30" width="40" />
        </a>
        <a href="https://www.linkedin.com/in/anisha-premi/">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/LinkedIn.svg" alt="anisha-premi" height="30" width="40">
        </a>
    </td>
    <td  align="center">
      <img src="https://github.com/Mahak008/IGDTUW-CMS/assets/88129955/8ba3e687-6a1f-4e46-a455-107b0833bf1c" width=150px height=150px />
      <h4>Bharti Rana</h4>
        <a href="https://github.com/bharti2430">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Github-Dark.svg" alt="bharti2430" height="30" width="40" />
        </a>
        <a href="https://www.linkedin.com/in/bharti-rana304/">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/LinkedIn.svg" alt="bharti-rana304" height="30" width="40">
        </a>
    </td>
    <td  align="center">
      <img src="https://github.com/Mahak008/IGDTUW-CMS/assets/88129955/3cf94208-f960-47b7-95c0-d472f7f52877" width=150px height= 150px />
      <h4>Mahak Garg</h4>
        <a href="https://github.com/Mahak008">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Github-Dark.svg" alt="Mahak008" height="30" width="40" />
        </a>
        <a href="https://linkedin.com/in/mahakgarg" target="_blank">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/LinkedIn.svg" alt="mahakgarg" height="30" width="40">
        </a>
    </td>
    <td  align="center">
      <img src="https://github.com/Mahak008/IGDTUW-CMS/assets/88129955/07d4831b-ff78-430d-8ca7-a28a07359a38" width=150px height=150px />
      <h4>Sapna Singhal</h4>
        <a href="https://www.github.com/sapnasinghal22">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Github-Dark.svg" alt="sapnasinghal22" height="30" width="40" />
        </a>
        <a href="https://www.linkedin.com/in/sapnasinghal-/">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/LinkedIn.svg" alt="sapnasinghal-/" height="30" width="40">
        </a>
    </td>
    <td  align="center">
      <img src="https://github.com/Mahak008/IGDTUW-CMS/assets/88129955/63539ef0-2ab6-453c-8694-3df550159ebd" width=150px height=150px />
      <h4>Shruti Jain</h4>
        <a href="https://github.com/shrutijn02">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/Github-Dark.svg" alt="shrutijn02" height="30" width="40" />
        </a>
        <a href="https://www.linkedin.com/in/shruti-jain-a0840724a/">
          <img align="center" src="https://github.com/tandpfun/skill-icons/blob/main/icons/LinkedIn.svg" alt="shruti-jain-a0840724a" height="30" width="40" />
        </a>
    </td>
  </tr>
</table>
