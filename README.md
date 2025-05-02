Cashier Consumer - Restaurant Order Management System


Overview

The Cashier Consumer is a Consumer module in the Restaurant Order Management System, built using the Open Service Gateway Initiative (OSGi) architecture. This module subscribes to the Billing Service to process customer payments and send receipts via an external service. It ensures seamless payment handling and receipt delivery, enhancing the restaurant's operational efficiency.
Operating within the modular OSGi ecosystem, this Consumer interacts with the Billing Service, maintaining loose coupling and enabling scalability.


Functionality

Payment Processing: Listens to the Billing Service to retrieve billing details and processes customer payments.
Receipt Delivery: Sends receipts to customers via an external service after successful payment.


Project Structure

Sewmi_CashierConsumer/src: Contains the source code for the Cashier Consumer.
cashierconsumer/ConsumerActivator.java: OSGi activator for subscribing to the Billing Service and handling payments.


Setup and Usage

Ensure you have an OSGi framework installed (e.g., Apache Felix or Equinox).
Clone the repository and navigate to the Sewmi_CashierConsumer directory.
Build the module using the provided build.properties file.
Deploy the bundle in your OSGi framework.
Ensure the Billing Service (Producer 3) is running, as this Consumer depends on it.


Dependencies

Java SE 1.8
OSGi Framework (e.g., Apache Felix)
Billing Service (Producer 3)
