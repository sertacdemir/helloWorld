from datetime import datetime


user_input = input("Enter your goal separated with deadline ex: learn python:18.06.2021\n")
input_list = user_input.split(":")

goal = input_list[0]
deadline = input_list[1]
print(datetime.strptime(deadline, "%d.%m.%Y"))
deadline_date = datetime.strptime(deadline, "%d.%m.%Y")
today_date = datetime.today()

time_till = deadline_date - today_date
hours_till = int(time_till.total_seconds() / 60 / 60)
print(f"Time remaining for your goal: {goal} is {hours_till} hours")
