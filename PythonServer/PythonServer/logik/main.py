from django.contrib.auth import authenticate, login
from django.contrib.auth.models import User
from django.http import HttpResponse


def mainconnect(request):
    username = None
    password = None
    if 'username' in request.GET and request.GET['username']:
        username = request.GET['username']
    if 'password' in request.GET and request.GET['password']:
        password = request.GET['password']
    user = authenticate(username=username, password=password)
    if user is not None:
        if user.is_active:
            login(request, user)
            return HttpResponse("Авторизация успешная")
        else:
            return HttpResponse("Ошибка авторизации")
    else:
        return HttpResponse("Ошибка авторизации")


def mainlogin(request):
    username = None
    password = None
    first_name = None
    last_name = None
    surename = None
    group = None
    gender = None
    number = None
    if 'username' in request.GET and request.GET['username']:
        username = request.GET['username']
    if 'password' in request.GET and request.GET['password']:
        password = request.GET['password']
    if 'first_name' in request.GET and request.GET['first_name']:
        first_name = request.GET['first_name']
    if 'last_name' in request.GET and request.GET['last_name']:
        last_name = request.GET['last_name']
    if 'surename' in request.GET and request.GET['surename']:
        surename = request.GET['surename']
    if 'group' in request.GET and request.GET['group']:
        group = request.GET['group']
    if 'gender' in request.GET and request.GET['gender']:
        gender = request.GET['gender']
    if 'number' in request.GET and request.GET['number']:
        number = request.GET['number']
    if first_name is None or last_name is None or surename is None or group is None or gender is None or number is None:
        return HttpResponse("0")
    user = authenticate(username=username, password=password)
    if user is not None:
        if user.is_active:
            login(request, user)
            return HttpResponse("0")
        else:
            # Return a 'disabled account' error message
            user = User.objects.create_user(username, 'univer@box.com', password)
    else:
        user = User.objects.create_user(username, 'univer@box.com', password)
    user.first_name = first_name
    user.last_name = last_name
    user.surename = surename
    user.group = group
    user.gender = gender
    user.number = number

    return HttpResponse("1")
