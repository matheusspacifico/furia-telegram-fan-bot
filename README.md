# FURIA Telegram Fan Bot

## Description
This is my entry for FURIA's internship technical challenge.
The FURIA Telegram Fan Bot is a Telegram bot dedicated to FURIA Esports fans. It provides information about live matches, upcoming games, and links to FURIA's social media.

## Features

### Available Commands
- `/start` - Displays the welcome message and lists all available commands
- `/upcoming` - Shows upcoming matches for FURIA and FURIA Academy
- `/live` - Checks if FURIA or FURIA Academy is currently playing
- `/torcida` - Displays links to FURIA's social media

## Technologies Used
- **Kotlin** - Main programming language
- **Gradle** - Build automation system
- **OkHttpClient** - Fetching URL service from okhttp3
- **[HLTV-API Unofficial API](https://hltv-api.vercel.app/api/matches.json)** - Unofficial API used to get matches

## Dependencies
- **kotlin-telegram-bot** - API for Telegram Bot interaction
- **dotenv-kotlin** - Environment variable management
- **org.json** - JSON processing

## Setup and Installation

### Prerequisites
- JDK 21
- Gradle
- Telegram bot token (obtained through [@BotFather](https://t.me/BotFather))

### Installation Steps
1. Clone the repository:
   ```
   git clone https://github.com/your-username/furia-telegram-fan-bot.git
   cd furia-telegram-fan-bot
   ```

2. Configure environment variables:
   - Create a `.env` file in the `src/main/resources/` folder
   - Add your Telegram bot token:
     ```
     BOT_TOKEN=your-token-here
     ```

3. Build the project:
   ```
   ./gradlew build
   ```

## Running the Bot

To start the bot locally:
```
./gradlew run
```

## Detailed Features

### Live Match Verification
The bot checks if there are any FURIA or FURIA Academy matches happening at the moment (within a 3-hour window) and provides details about the event, opponent, and time.

### Upcoming Matches
Lists all upcoming matches for FURIA and FURIA Academy, including information about the event, opponent, and date/time (in Brasília timezone - GMT-3).

### Virtual Fan Section
Provides links to all of FURIA's social media, including Twitter, Instagram, Merchandise, TikTok, Twitch, and YouTube.

## Contribution
Contributions are welcome! Feel free to open issues or submit pull requests with improvements.
