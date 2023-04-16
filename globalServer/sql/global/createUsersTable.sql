CREATE TABLE IF NOT EXISTS [users] (
[id] INTEGER  PRIMARY KEY NOT NULL,
[username] TEXT  UNIQUE NOT NULL,
[email] TEXT  UNIQUE NOT NULL,
[name] TEXT DEFAULT 'Иван' NULL,
[surname] TEXT DEFAULT 'Иванов' NULL,
[icon] TEXT DEFAULT 'https://raw.githubusercontent.com/penakelex/Hackathon_Project_Backend/main/default.jpg' NULL,
[dateOfBirth] DATE  NULL,
[password_hash] TEXT  NOT NULL
)
