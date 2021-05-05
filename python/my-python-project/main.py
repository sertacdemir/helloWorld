# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.

# import helper
import os
from helper import validate_and_execute


def print_hi(name):
    print(f'Hi, {name} the operation name is {os.name}')  # Press ⌘F8 to toggle the breakpoint.


"""
    The block of the comment before main part
    list example: ["January", "February", "March", "March"]
    set example: {"February", "March", "January"}
    dictionary is key value pairs 
"""
if __name__ == '__main__':
    print_hi('PyCharm')
    user_input = ""
    while user_input != "exit":
        """
        user_input = input("Hey user enter the number of days as a comma separated list : \n")
        for num_of_days_element in set(user_input.split(", ")):
            validate_and_execute()
        """
        user_input = input("Hey user enter the number of days and conversion unit column separated: \n")
        try:
            days_and_unit = user_input.split(":")
            days_and_unit_dictionary = {"days": days_and_unit[0], "unit": days_and_unit[1]}
            validate_and_execute(days_and_unit_dictionary)
        except:
            print("Wrong value")



