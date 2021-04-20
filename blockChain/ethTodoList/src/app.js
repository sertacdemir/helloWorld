App = {
  loading:false,
  contracts:{},

  load: async () => {
    console.log("app is loading...")
    await App.loadWeb3();
    await App.loadAccount();
    await App.loadContract();
    await App.render();
  },
  // injecting web3 is not neceaasry anymore
  loadWeb3: async () => {
    const ethEnabled = () => {
      if (window.ethereum) {
        window.web3 = new Web3(window.ethereum);
        window.ethereum.enable();
        return true;
      }
      return false;
    }
  },

  loadAccount: async() => {
    App.account = await ethereum.request({ method: 'eth_accounts' });
    console.log(App.account);
  },

  loadContract: async() => {
    const todoList = await $.getJSON('TodoList.json');
    App.contracts.TodoList = TruffleContract(todoList);
    App.contracts.TodoList.setProvider(window.web3.currentProvider);

    App.todoList = await App.contracts.TodoList.deployed();
    console.log(App.todoList);
  },

  render: async() => {
    //prevent double rendering
    if(App.loading) {
      return;
    }
    App.setLoading(true);

    //render account
    $('#account').html(App.account);
    //renderTasks
    await App.renderTasks();

    App.setLoading(false);

  },

  renderTasks: async() => {
    //Load the tasks from the block Blockchain
    const taskCount = await App.todoList.taskCount();
    const $taskTemplate = $('.taskTemplate');
    console.log("Task number is " + taskCount);

    //render each task
    for(var i=1; i<=taskCount; i++) {
      const task = await App.todoList.tasks(i);
      const taskId = parseInt(task[0]);
      const taskContent = task[1];
      const taskCompleted = task[2];

      const $newTaskTemplate = $taskTemplate.clone();
      $newTaskTemplate.find('.content').html(taskContent);
      $newTaskTemplate.find('input')
                      .prop('name', taskId)
                      .prop('checked', taskCompleted)
                      //.on('click',App.toggleCompleted);
      if(taskCompleted) {
        $('#completedTaskList').append($newTaskTemplate);
      } else {
        $('#taskList').append($newTaskTemplate);
      }

      //show the task
      $newTaskTemplate.show();
    }
  },

  createTask:async() => {
    App.setLoading(true)
    const content = $('#newTask').val();
    const fromVal = App.account[0];
    await App.todoList.createTask(content,{from: fromVal});
    window.location.reload();
  },

  setLoading:(boolean) => {
    App.loading = boolean;
    const loader = $('#loader');
    const content = $('#content');

    if(boolean){
      loader.show();
      content.hide();
    } else {
      loader.hide();
      content.show();
    }
  }
}



$(() => {
  $(window).load(() => {
    App.load()
  })
})
