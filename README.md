# 🛫 Flight Booking System

Kompleksowy system rezerwacji lotów napisany w Javie, demonstrujący zasady programowania obiektowego (OOP) poprzez różne typy połączeń lotniczych.

## ✨ Funkcjonalności

### 🎯 Główne Funkcjonalności

- **Wielotypowe loty**: Krajowe, międzynarodowe i czarterowe
- **System rezerwacji**: Zapobieganie nadmiernym rezerwacjom (overbooking)
- **Zarządzanie płatnościami**: Różne metody płatności i kalkulacja cen
- **Historia rezerwacji**: Śledzenie wszystkich transakcji
- **Wyszukiwanie**: Loty według numeru lub destynacji

### 📊 Typy Lotów

| Typ                     | Opis                | Specyfika                                |
| ----------------------- | ------------------- | ---------------------------------------- |
| **DomesticFlight**      | Loty krajowe        | Podatek krajowy 5%                       |
| **InternationalFlight** | Loty międzynarodowe | Opłata celna $50 + wymagania paszportowe |
| **CharterFlight**       | Loty czarterowe     | Operator czarteru + specjalne warunki    |

## 🏗️ Architektura Systemu

### Diagram Klas

Bookable ═══════════╗
║
╠═▶ Flight (abstract) ──┐
Describable ════════╣ │
╚═▶ DomesticFlight │
InternationalFlight│
CharterFlight ◀────┘
▲
┌──────────┴──────────┐
FlightManager PaymentProcessor

text

### Struktura Plików

flight-booking-system/
├── 📄 Bookable.java # Interfejs rezerwacji
├── 📄 Describable.java # Interfejs opisu
├── 📄 Flight.java # Abstrakcyjna klasa bazowa
├── 📄 DomesticFlight.java # Loty krajowe
├── 📄 InternationalFlight.java # Loty międzynarodowe
├── 📄 CharterFlight.java # Loty czarterowe
├── 📄 FlightManager.java # Menadżer systemu
├── 📄 PaymentProcessor.java # System płatności
├── 📄 App.java # Główna aplikacja
└── 📄 FlightTest.java # Testy jednostkowe

## 🚀 Szybki Start

### Wymagania

- Java JDK 8 lub nowszy
- Podstawowa znajomość Javy

### Kompilacja i Uruchomienie

# Kompilacja wszystkich plików

javac \*.java

# Uruchomienie głównej aplikacji

java App

# Uruchomienie testów

java FlightTest
