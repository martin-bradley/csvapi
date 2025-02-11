# CSV API Application

## Overview

This project is a Spring Boot application that provides a REST API for reading CSV files and returning the data as a list of lists. The application uses a service to read the CSV file, process the data, and convert coordinates from OSGB36 to WGS84.

## Features

- **Read CSV Files:** Reads CSV files from a provided URL and returns the data as a list of lists.
- **Coordinate Conversion:** Converts coordinates from OSGB36 to WGS84 format.
- **Spring Boot:** Leverages the Spring Boot framework for easy setup and configuration.

## Requirements

- Java 8 or higher
- Spring Boot 2.0 or higher
- Maven 3.0 or higher

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/csv-api.git
   cd csv-api
