<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="http://twitter.github.com/bootstrap/assets/css/bootstrap-1.1.1.min.css">
    <style>
        .todos .action { width: 40px; }
        .todos .title { cursor: pointer; }
        .task.done .title { text-decoration: line-through; }
    </style>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    <script type="text/javascript" src="js/todos.js"></script>
    <title>4SH Todos</title>
</head>
<body>
<div class="container">
    <div class="topbar-wrapper" style="z-index: 5;">
        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <h3><a href="#">4SH Todos</a></h3>
                    <ul>
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#">More lists</a></li>
                    </ul>
                    <form action="">
                        <input type="text" placeholder="Search">
                    </form>
                    <ul class="nav secondary-nav">
                        <li class="menu">
                            <a href="#" class="menu">Xavier Hanin</a>
                            <ul class="menu-dropdown">
                                <li><a href="#">Profile</a></li>
                                <li><a href="#">Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Disconnect</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div><!-- /fill -->
        </div><!-- /topbar -->
    </div>

    <h1>4SH Todos</h1>
    <table class="todos zebra-striped">
        <thead>
        <tr><th class="action"></th><th class="title">Task</th></tr>
        </thead>
        <tbody>
        <tr class="task" id="task1"><td class="action"></td><td class="title">My task 1</td></tr>
        <tr class="task" id="task2"><td class="action"></td><td class="title">My task 2</td></tr>
        <tr class="task" id="task3"><td class="action"></td><td class="title">My task 3</td></tr>
        </tbody>
    </table>

    <div class="clearfix">
        <label for="newTaskTitle">New task:</label>
        <div class="input">
            <input class="xlarge" id="newTaskTitle" name="newTaskTitle" size="30" type="text">
            <button id="newTask" class="btn primary">Add</button>
        </div>
    </div>

    <div id="success" class="alert-message success">
        <a class="close" href="#">×</a>
        <p><strong>Well done!</strong> You successfully added a task.</p>
    </div>
</div>
</body>
</html>