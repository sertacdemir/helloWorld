1. We need nodeJs. It should be installed.
2. Block chain test ganache is needed.
  2.1 Ganache allows you to create a private Ethereum blockchain for you to run tests, execute commands,
  and inspect state while controlling how the chain operates. It gives you the ability to perform all actions
  you would on the main chain without the cost.
3.Install truffle studio
  3.1 sudo npm install truffle -g
4. Add metamask extension to google chrome
5. initiate a project
  5.1 truffle init
6. create package with correct versions
  6.1 npm view truffle-contract versions
7. Create a TodoList contract under contracts
  7.1 compile the project with truffle compile to check the contract
8. Edit truffle-config.js
  8.1 uncommend development
9.Create a migration under Migrations
  9.1 2_deploy_contracts.js
10. Run the migration and deploy the smart contracts to block chain
  10.1 Make sure the ganage is running
  10.2 truffle migrate
  10.3. Check the deploymment
    10.3.1. Go to console: truffle console
    10.3.2. assign deployment to var: todoList = await TodoList.deployed() //Interactions are async that is why await
    10.3.3. check the variable: todoList
      10.3.3.1 Check the address: todoList.address
      10.3.3.2 Check the taskCount that is creates as state variable: todoList.taskCount() //should be zero
      10.3.3.3 examle: taskCount = await todoList.taskCount() and than check taskCount.toNumber()
11.
