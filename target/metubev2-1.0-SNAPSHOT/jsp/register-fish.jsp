<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
    <div class="container-fluid">
        <header>
            <c:import url="templates/navbar.jsp"/>
        </header>
        <main>
            <hr class="my-2"/>
            <div class="text-center mb-3">
                <h1>Register</h1>
            </div>
            <hr class="my-2">
            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="form-holder text-center">
                        <form class="form-inline" action="/register" method="POST">
                            <fieldset>
                                <div class="control-group">
                                    <label class="control-label h3 mb-2" for="fishName">Fish Name</label>
                                    <div class="controls">
                                        <input type="text" id="fishName" name="fishName" placeholder=""
                                               class="input-xlarge">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label h3 mb-2" for="fishFamily">Firsh Family -
                                        ENUM!!!</label>
                                    <div class="controls">
                                        <input type="text" id="fishFamily" name="fishFamily" placeholder=""
                                               class="input-xlarge">
                                    </div>
                                </div>
                                <br/>
                                <div class="control-group">
                                    <label class="control-label h3 mb-2" for="weigthInKilograms">Weigth in kg.</label>
                                    <div class="controls">
                                        <input type="number" id="weigthInKilograms" name="weigthInKilograms"
                                               placeholder=""
                                               class="input-xlarge">
                                    </div>
                                </div>
                                <br/>
                                <div class="control-group">
                                    <label class="control-label h3 mb-2" for="lengthInSentimeters">Length in sm</label>
                                    <div class="controls">
                                        <input type="number" id="lengthInSentimeters" name="lengthInSentimeters"
                                               placeholder=""
                                               class="input-xlarge">
                                    </div>
                                </div>
                                <br/>
                                <div class="control-group">
                                    <label class="control-label h3 mb-2" for="dam">Dam</label>
                                    <div class="controls">
                                        <input type="text" id="dam" name="dam" placeholder=""
                                               class="input-xlarge">
                                    </div>
                                </div>
                                <br/>
                                <div class="control-group">
                                    <div class="controls">
                                        <button class="btn btn-info">Register</button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
            <hr class="my-3"/>
        </main>
        <footer>
            <c:import url="templates/footer.jsp"/>
        </footer>
    </div>
</body>
</html>
