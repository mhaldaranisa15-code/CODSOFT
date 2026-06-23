
print("🤖 SmartBot: Hello! Type 'exit' to end the chat.")

while True:
    user = input("You: ").lower()

    if user == "exit":
        print("🤖 SmartBot: Goodbye! Have a nice day.")
        break

    elif user in ["hi", "hello", "hey"]:
        print("🤖 SmartBot: Hello! How can I help you?")

    elif "name" in user:
        print("🤖 SmartBot: My name is SmartBot.")

    elif "how are you" in user:
        print("🤖 SmartBot: I'm fine and ready to chat!")

    elif "joke" in user:
        print("🤖 SmartBot: Why do programmers prefer dark mode? Because light attracts bugs!")

    elif "weather" in user:
        print("🤖 SmartBot: I can't check live weather, but I hope it's a good day outside!")
    elif "skin" in user:
        print("🤖 SmartBot: Use aloe vera for skin care! It is very beneficial!")

    elif user.startswith("add"):
        try:
            parts = user.split()
            num1 = int(parts[1])
            num2 = int(parts[2])
            print("🤖 SmartBot: Sum =", num1 + num2)
        except:
            print("🤖 SmartBot: Use format -> add 10 20")
    elif user.startswith("subtract"):
        try:
            parts = user.split()
            num1 = int(parts[1])
            num2 = int(parts[2])
            print("🤖 SmartBot: Difference =", num1 - num2)
        except:
            print("🤖 SmartBot: Use format -> subtract 10 20")

    elif "thank" in user:
        print("🤖 SmartBot: You're welcome!")

    else:
        print("🤖 SmartBot: Sorry, I don't understand that.")