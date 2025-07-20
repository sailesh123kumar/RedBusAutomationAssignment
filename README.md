🚌 RedBus Bus Search Automation (Selenium + Java)
Automate the RedBus.in website using Selenium WebDriver and Java to simulate a complete user journey — from selecting cities to extracting and printing filtered bus results.
✨ Features
🔍 Search buses from Chennai to Bangalore
🛰️ Apply Live Tracking and AC (Air Conditioned) filters
🔄 Handle lazy loading / infinite scrolling
📋 Extract and print all bus operator names
🧮 Print the total number of filtered buses
🛠️ Technologies Used
Tool
Purpose

Java (JDK 8+)
Programming language

Selenium WebDriver
Browser automation

ChromeDriver
Control Chrome browser

Maven/Gradle
Dependency management (optional)

TestNG/JUnit
Test execution framework (optional)

🚦 Step-by-Step Automation Flow

1️⃣ Launch Browser & Open Website
Open Chrome
Navigate to https://www.redbus.in

2️⃣ Set Travel Route
Click the From input box
Enter: "Chennai"
Wait for suggestions → select Chennai
Click the To input box
Enter: "Bangalore"
Wait for suggestions → select Bangalore

3️⃣ Search Buses
Click the "Search Buses" button

4️⃣ Apply Filters
Enable Live Tracking filter
Enable AC (Air Conditioned) filter

5️⃣ Handle Lazy Loading
Scroll down continuously
Wait until "End of list" message is visible

6️⃣ Extract & Print Results
Collect all bus operator names
Print each name
Print total count of buses displayed

📦 Project Structure

RedBusAutomation/
├── src/
│   └── main/java/
│       └── RedBusAutomation.java
├── pom.xml               # If using Maven
└── README.md


💻 Sample Output

Bus Operator: KPN Travels

Bus Operator: SRS Travels

Bus Operator: Orange Tours and Travels

...

Total Buses Found: 18

▶️ How to Run the Automation Script
1. Clone the Repository
git clone https://github.com/yourusername/RedBusAutomation.git

3. Install Dependencies (if using Maven)
mvn clean install

5. Run the Script
java RedBusAutomation
Make sure ChromeDriver is in your system path or set via System.setProperty.
⚠️ Notes & Tips
Use WebDriverWait for reliable interaction
Use JavascriptExecutor to scroll through the lazy-loaded list

UI elements (selectors) may change — inspect the site if automation fails
