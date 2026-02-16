# UEFA Champions League Simulator 

An advanced, probability-driven simulator for the new UEFA Champions League format (2024/2025 Swiss-system tournament). 

Unlike basic simulators that rely on simple random number generation, this project utilizes real-world mathematical models, including the **Elo Rating System** and **Poisson Distribution**, to accurately simulate match outcomes and generate realistic league tables.

## The Simulation Engine (Mathematics & Logic)

The core of the simulation resides in the `MatchService` class, which handles the complex logic of predicting football matches based on team strength.

### 1. Win Probability (Elo System)
Each team is assigned an Elo rating. The probability of Team A winning against Team B is calculated using the standard Elo sigmoid function, adjusted for a **Home Advantage** (constant of +50 Elo points for the home team):

$$P_{home} = \frac{1}{1 + 10^{-\frac{(Elo_{home} - Elo_{away}) + 50}{400}}}$$

### 2. Goal Generation (Poisson Distribution)
Football goals are rare, independent events, making them perfect for Poisson distribution modeling. 
Once the win probability is established, it dynamically scales the expected average goals ($\lambda$). The exact number of goals scored by a team is then drawn from the Poisson distribution:

$$P(k \text{ goals}) = \frac{\lambda^k e^{-\lambda}}{k!}$$

*Note: The model caps the maximum number of goals per team at 4 to prevent extreme statistical outliers during bulk simulations.*

## Key Features
* **New League Phase Format:** Fully implements the 36-team single-league format with 8 matchdays drawing from 4 seeding pots.
* **Dynamic Table Calculation:** Automatically calculates the standings based on official UEFA tie-breaking rules (Points $\rightarrow$ Goal Difference $\rightarrow$ Goals Scored).
* **Object-Oriented Design:** Clean entity structures for `Team`, `Match`, and `TeamStandings`.

## Tech Stack
* **Language:** Java (Core)
* **Architecture:** Service-based logic
* **Data Structures:** Java Collections Framework (Maps, Lists, sorting via Comparators)
* **Math:** Built-in `java.lang.Math` for statistical distributions

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/gr4b0wski/uefa_cl_simulator_project.git](https://github.com/gr4b0wski/uefa_cl_simulator_project.git)
