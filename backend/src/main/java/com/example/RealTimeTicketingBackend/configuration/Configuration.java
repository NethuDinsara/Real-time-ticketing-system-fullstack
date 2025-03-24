package com.example.RealTimeTicketingBackend.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;


    public void init() {
        saveConfig();
        loadConfig();
    }

    public void saveConfig() {
        try (PrintWriter file = new PrintWriter("configuration.txt")) {
            file.println("{");
            file.println("\"Total Number of Tickets\": " + this.totalTickets + ",");
            file.println("\"Ticket Release Rate\": " + this.ticketReleaseRate + ",");
            file.println("\"Customer Retrieval Rate\": " + this.customerRetrievalRate + ",");
            file.println("\"Max Ticket Capacity\": " + this.maxTicketCapacity);
            file.println("}");
            System.out.println("Configuration saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while saving");
        }
    }


    public void loadConfig() {
        StringBuilder configContent = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("configuration.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                configContent.append(line.trim());
            }

            // Parse JSON-like structure
            String config = configContent.toString();
            parseConfig(config); // Call a method to parse and set fields
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while loading configuration.");
        }
    }

    private void parseConfig(String config) {
        try {
            config = config.replace("{", "").replace("}", ""); // Remove braces
            String[] entries = config.split(",");

            for (String entry : entries) {
                String[] keyValue = entry.split(":");
                String key = keyValue[0].trim().replace("\"", "");
                String value = keyValue[1].trim();

                switch (key) {
                    case "Total Number of Tickets":
                        this.totalTickets = Integer.parseInt(value);
                        break;
                    case "Ticket Release Rate":
                        this.ticketReleaseRate = Integer.parseInt(value);
                        break;
                    case "Customer Retrieval Rate":
                        this.customerRetrievalRate = Integer.parseInt(value);
                        break;
                    case "Max Ticket Capacity":
                        this.maxTicketCapacity = Integer.parseInt(value);
                        break;
                    default:
                        System.out.println("Unknown configuration key: " + key);
                }
            }

            System.out.println("Configuration loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while parsing configuration.");
        }
    }


    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }





    @Override
    public String toString() {
        return "Configuration [totalTickets=" + totalTickets + ", ticketReleaseRate=" + ticketReleaseRate+", customerRetrievalRate="+customerRetrievalRate+", maxTicketCapacity="+maxTicketCapacity+"]";
    }
}
