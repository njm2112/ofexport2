
// Useful Resources:
// https://developer.apple.com/library/mac/releasenotes/InterapplicationCommunication/RN-JavaScriptForAutomation/index.html
// Intro: http://pixelsnatch.com/blog/2014/10/24/using-javascript-with-omnifocus.html
// Projects: https://github.com/brandonpittman/omnifocus
// Key file: https://github.com/brandonpittman/OmniFocus/blob/master/OmniFocusLibrary.js

var app = Application('OmniFocus');
app.includeStandardAdditions = true;

var doc = app.defaultDocument;

function nameOf (o) {
    if (o == null) {
        return null;
    }
    else  {
        return o.name();
    }
}

function applyFilter(x, filter) {
    if(typeof(filter)==='undefined') {
        return x;
    }
    else {
        return x.whose(filter);
    }
}

/*
 * ADAPTATION
 */
function adaptFolder (o) {
    return {
        name: o.name(),
        id: o.id(),
    }
}

function adaptProject (o) {
    return {
        name: o.name(),
        context: nameOf(o.context()),
        id: o.id(),
        note: o.note(),
        deferDate: o.deferDate(),
        dueDate: o.dueDate(),
        completionDate: o.completionDate(),
        completed: o.completed(),
        sequential: o.sequential(),
        flagged: o.flagged()
    }
}

function adaptTask (o) {
    return {
            name: o.name(),
            containingProject: nameOf(o.containingProject()),
            context: nameOf(o.context()),
            id: o.id(),
            note: o.note(),
            deferDate: o.deferDate(),
            dueDate: o.dueDate(),
            completionDate: o.completionDate(),
            completed: o.completed(),
            sequential: o.sequential(),
            next: o.next(),
            blocked: o.blocked(),
            flagged: o.flagged()
    };
}

function adaptContext (o) {
    return {
        name: o.name(),
        id: o.id(),
    }
}

function adaptFolders (folders) {
    result = [];
    for (i in folders) {
        result.push (adaptFolder(folders[i]));
    }
    return result;
}

function adaptContexts (contexts) {
    result = [];
    for (i in contexts) {
        result.push (adaptContext(contexts[i]));
    }
    return result;
}

function adaptProjects (projects) {
    result = [];
    for (i in projects) {
        result.push (adaptProject(projects[i]));
    }
    return result;
}

function adaptTasks (tasks) {
    result = [];
    for (i in tasks) {
        result.push (adaptTask(tasks[i]));
    }
    return result;
}

/*
 * LOADING
 */

function _getFolders(filter) {
    return applyFilter(doc.flattenedFolders, filter);
}

function _getContexts(filter) {
    return applyFilter(doc.flattenedContexts, filter);
}

function _getProjects(filter) {
    return applyFilter(doc.flattenedProjects, filter);
}

function getFolders(filter) {
    return adaptFolders(_getFolders(filter));
}

function getContexts(filter) {
    return adaptContexts(_getContexts(filter));
}

function getProjects(filter) {
    return adaptProjects(_getProjects(filter));
}

function getAllTasksFromContext(id, filter) {
    var context = _getContexts({id : id})[0];
    return adaptTasks(applyFilter(context.tasks, filter));
}

function getRemainingTasksFromContext(id, filter) {
    var context = _getContexts({id : id})[0];
    return adaptTasks(applyFilter(context.remainingTasks, filter));
}

function getAvailableTasksFromContext(id, filter) {
    var context = _getContexts({id : id})[0];
    return adaptTasks(applyFilter(context.availableTasks, filter));
}

function getAllTasksFromProject(id, filter) {
    var project = _getProjects({id : id})[0];
    var rootTask = project.rootTask();
    return adaptTasks(applyFilter(rootTask.flattenedTasks, filter));
}

function getAllProjectsFromFolder(id, filter) {
    var folder = _getFolders({id : id})[0];
    return adaptProjects(applyFilter(folder.flattenedProjects, filter));
}

function getNextTaskFromProject(id, filter) {
    var project = _getProjects({id : id})[0];
    var nextTask = project.nextTask();
    if (nextTask == null) {
        return null;
    }
    else {
        tasks = applyFilter([nextTask], filter);
        if (tasks.length == 0) {
            return null;
        }
        return adaptTask (tasks[0]);
    }
}

function getAllTasksFromInbox (filter) {
    return adaptTasks(applyFilter(doc.inboxTasks, filter));
}

/*

function completedTodayFilter (tasks) {
    var thisMorning = new Date();
    thisMorning.setHours(0);
    return {
        _and: [
              {completed: true},
              {completionDate: { '>=' : thisMorning}}
        ]};
}

function getCompletedTodayTasksFromContext(name) {
    var context = _getContexts(name);
    return adaptTasks(context.tasks.whose(completedTodayFilter ()));
}
function createInboxItem(name) {
    var inbox = app.defaultDocument.inboxTasks;
    var task = app.InboxTask({name : name});
    inbox.push(task);
}

function bringToForeground() {
    app.activate();
}

function log(message) {
    var date = new Date();
    console.log(date + ': ' + message);
}

function logInboxTaskNames() {
    var tasks = app.defaultDocument.inboxTasks();
    for (i in tasks) {
        var task = tasks[i];
        log(task.name());
    }
}

function allTasks() {
    return doc.flattenedTasks.whose({completed: false})();
}
*/