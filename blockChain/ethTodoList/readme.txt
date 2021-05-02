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
  7.1 compile the project with 'truffle compile" to check the contract
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
11.List tasks in contract TodoList
  11.1. truffle compile: for compiling again
  11.2. Deploy a new copy of the contract : truffle migrate --reset
  11.3. check the initial list: truffle console
    11.3.1. get copy of todoList: todoList = await TodoList.deployed()
    11.3.2. to check get the first element: task = await todoList.tasks(1)
    11.3.3. check the task: task
12. Create client side application in src folder.
  12.1. add web3 JavaScript part to the app.js
  12.2. Web3 is added to package and app.js
13. configure configuration of lite server to run as web server.
  13.1. Create json file: bs-config.json
14. Add ganage first account to metamask in chrome
  14.1. change the port of the local network
  14.2. import the account with private key
  14.3. After the configuration we should see connected in refreshing the page
15. After restart of computer
  15.1. run the command truffle migrate --reset to reset the smart contracts
  15.2. Because I have used quick start I regenerate ganage wallet and add the key again
  15.3. rerun the app npm run dev
  15.4. From the chrome metatask add again the account and manually reconnect the web site and connected icon was there
