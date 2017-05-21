var app = angular.module("mainLibraryApp", ['ngResource', 'ngSanitize', 'ngRoute', 'angular-loading-bar']);

//////////////directive for compiling getting from server parts of html
app.directive('dynamic', function ($compile) {
    return {
        restrict: 'A',
        replace: true,
        link: function (scope, ele, attrs) {
            scope.$watch(attrs.dynamic, function (html) {
                ele.html(html);
                $compile(ele.contents())(scope);
            });
        }
    };
});
app.directive('validFile', function () {
    return {
        require: 'ngModel',
        link: function (scope, el, attrs, ngModel) {

            //change event is fired when file is selected
            el.bind('change', function () {
                scope.$apply(function () {
                    ngModel.$setViewValue(el.val());
                    ngModel.$render();
                });
            });
        }
    };
});
//////////////////////////////////////////////////////////////////////////
//////////Factory for work with books///////////////
app.factory('Book', ['$resource', function ($resource) {
    //$resource() function returns an object of resource class
    return $resource(
        '/books/:idbook',
        {idbook: '@idbook'},
        {
            update: {
                method: 'PUT'
            }

        }
    );
}]);

/////////////////////////////////////////////////////////////////////////
/////////////Directory validate choose file
app.directive('validFile', function () {
    return {
        require: 'ngModel',
        link: function (scope, el, attrs, ngModel) {

            //change event is fired when file is selected
            el.bind('change', function () {
                scope.$apply(function () {
                    ngModel.$setViewValue(el.val());
                    ngModel.$render();
                    var file = el[0].files[0];
                    if (file.size > 20000000) {
                        ngModel.$setValidity("length", false);
                    } else {
                        ngModel.$setValidity("length", true);
                    }
                });
            });
        }
    };
});
////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
app.controller("mainLibraryController", ['$sce', '$scope', '$http', 'Book', 'cfpLoadingBar', function ($sce, $scope, $http, Book, cfpLoadingBar) {
    $scope.book = new Book();
    $scope.listBooks = [];
    $scope.showBooksLibrary = function () {
        ///get list groups
        $scope.listBooks = Book.query(function (success) {
                if ($scope.listBooks.length === 0)
                    $http.get("/booksList/booksListEmpty").then(function successCallback(response) {
                        angular.element(document.querySelector('.filter')).empty();
                        angular.element(document.querySelector('.answerMessages')).empty();

                        $scope.html2 = $sce.trustAsHtml(response.data);
                    }, function errorCallback(response) {
                        alert("Ошибки работы сервиса");
                    });
                else {
                    $http.get("/books/view").then(function successCallback(response) {
                        angular.element(document.querySelector('.bookBlock')).empty();
                        $scope.html = $sce.trustAsHtml(response.data);
                    }, function errorCallback(response) {
                        alert("Ошибки работы сервиса");
                    });
                }
            },
            function (error) {
                $http.get("/booksList/404Eror").then(function successCallback(response) {
                    angular.element(document.querySelector('.answerMessages')).empty();
                    $scope.html2 = $sce.trustAsHtml(response.data);
                }, function errorCallback(response) {
                    alert("Ошибки работы сервиса");
                });
            }
        );

    };

    ////////////////////////delete message from server
    $scope.deleteAlert = function () {
        angular.element(document.querySelector('.Myclose')).remove();
    };
    /////////////////////////////////////////////////////////////////
    $scope.bookDetails = function (book) {
        angular.element(document.querySelector('.file')).empty();
        $scope.book = book;
        $http.get("/bookDetails/view").then(function successCallback(response) {
            $scope.fileHtml = "";

            $scope.html = $sce.trustAsHtml(response.data);
        }, function errorCallback(response) {
            alert("Ошибки работы сервиса");
        });
    };
    ////////////////////////////////////////////////////////////////////
    $scope.deleteBook = function (id) {

        var book = Book.get({idbook: id}, function () {
            book.$delete(function () {
                    $http.get("/book/SuccessDelete").then(function successCallback(response) {
                        angular.element(document.querySelector('.bookBlock')).empty();
                        $scope.html2 = $sce.trustAsHtml(response.data);
                    }, function errorCallback(response) {
                        alert("Ошибки работы сервиса");
                    });
                },
                function (errorResult) {
                    if (errorResult.status === 404)
                        $http.get("/book/ErrorDelete").then(function successCallback(response) {
                            $scope.html2 = $sce.trustAsHtml(response.data);
                        }, function errorCallback(response) {
                            alert("Ошибки работы сервиса");
                        });
                     if (errorResult.status === 409)
                         $http.get("/book/ErrorDeleteConflict").then(function successCallback(response) {
                             $scope.html2 = $sce.trustAsHtml(response.data);
                         }, function errorCallback(response) {
                             alert("Ошибки работы сервиса");
                         });
                });
        });
    };
    ////////////////////////////////////////////////////////////////////

    ///////////////////////Watch file
    var cache = true;
    $scope.watchBook = function (id) {
        $http({
            method: 'GET',
            url: '/document',
            params: {idbook: id},
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();
            if (headers['use-cache'] === "no")
                cache = false;
            else
                cache = true;
            try {
                var contentType = headers['mime'];
                var file = new Blob([data], {encoding: "UTF-8", type: contentType + ";charset=UTF-8"});
                var fileURL = window.URL.createObjectURL(file);
                var content = $sce.trustAsResourceUrl(fileURL);
                $scope.fileHtml = "<object   data=" + content + "   style='width: 100%; height: 500px;'></object>"

            } catch (ex) {
                console.log(ex);
            }
        }).error(function (data) {
            $http.get("/book/errorWatching").then(function successCallback(response) {
                $scope.html2 = $sce.trustAsHtml(response.data);
            }, function errorCallback(response) {
                alert("Ошибки работы сервиса");
            });
        }).finally(function () {

            cfpLoadingBar.complete();
            //after request make server delete downloaded file if it was not get from cache , save only zip
            if (!cache)
                $http.delete('/document/delete/' + id, {params: {idbook: id}}).error(function (data) {
                    alert("Ошибки сервера");
                });
        });
    };
    ///////////////////////Download book
    $scope.download = function (id) {
        $http({
            method: 'GET',
            url: '/document',
            params: {idbook: id},
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();
            if (headers['use-cache'] === "no")
                cache = false;
            else
                cache = true;
            var filename = headers['x-filename'];
            var contentType = headers['content-type'];
            var linkElement = document.createElement('a');
            try {

                var blob = new Blob([data], {type: contentType});
                var url = window.URL.createObjectURL(blob);

                linkElement.setAttribute('href', url);
                linkElement.setAttribute("download", filename);

                var clickEvent = new MouseEvent("click", {
                    "view": window,
                    "bubbles": true,
                    "cancelable": false
                });
                linkElement.dispatchEvent(clickEvent);
            } catch (ex) {
                console.log(ex);
            }
        }).error(function (data) {
            $http.get("/book/errorWatching").then(function successCallback(response) {
                $scope.html2 = $sce.trustAsHtml(response.data);
            }, function errorCallback(response) {
                alert("Ошибки работы сервиса");
            });
        }).finally(function () {
            cfpLoadingBar.complete();
            //after request make server delete downloaded file , save oly zip
            if (!cache)
                $http.delete('/document/delete/' + id, {params: {idbook: id}}).error(function (data) {
                    console.log(data);
                });
        });
    };
    //////////////add /edit --- get form
    $scope.addBookToLibrary = function () {
        $scope.book = new Book();
        $scope.signedList = [true, false]
        $http.get("/bookDetails/addBookForm/view").then(function successCallback(response) {
            angular.element(document.querySelector('.bookBlock')).empty();
            angular.element(document.querySelector('.filter')).empty();
            $scope.html = $sce.trustAsHtml(response.data);
        }, function errorCallback(response) {
            alert("Ошибки работы сервиса");
        });
    }
    $scope.checkboxModel = {
        value: true
    };
    $scope.submit = function (book) {
        angular.element(document.querySelector('.bookBlock')).empty();
        book.signed = $scope.checkboxModel.value;
        book.$save();
    }
    ////////////////////////////////////////////////
    $scope.ifExistBook = function (id) {
        for (var i = 0; i < $scope.listBooks.length; i++) {
            if ($scope.listBooks[i].idbook === id) {
                return true;
            }
        }
        return false;
    }
    //////////////////////////////////////////
    /* var self = this;
     self.getEditDescribingPartForm = function () {
     $http.get("/bookDetails/describePartForm/view").then(function successCallback(response) {
     angular.element(document.querySelector('.bookBlock')).empty();
     $scope.html = $sce.trustAsHtml(response.data);
     }, function errorCallback(response) {
     alert("Ошибки работы сервиса");
     });
     }*/
    $scope.edit = function (book) {

        $scope.addBookToLibrary();
        $scope.book = book;
    }
    /*  $scope.editContent = function (book) {
     $scope.book = book;
     self.getEditContentForm();

     }

     self.getEditContentForm = function () {
     $http.get("/bookDetails/updateContentForm/view").then(function successCallback(response) {
     angular.element(document.querySelector('.bookBlock')).empty();
     $scope.html = $sce.trustAsHtml(response.data);
     }, function errorCallback(response) {
     alert("Ошибки работы сервиса");
     });
     }
     */

    $scope.submitEdit = function () {
        self.updateBook();
    };
    self.updateBook = function () {
        $scope.book.$update(function () {
                $http.get("/book/updateSuccessMessage").then(function successCallback(response) {
                    angular.element(document.querySelector('.answerMessages')).empty();
                    $scope.html2 = $sce.trustAsHtml(response.data);
                }, function errorCallback(response) {
                    alert("Ошибки работы сервиса");
                });
            },
            function (errorResult) {
                if (errorResult.status === 404)
                    $http.get("/book/updateErrorMessage").then(function successCallback(response) {
                        angular.element(document.querySelector('.answerMessages')).empty();
                        $scope.html2 = $sce.trustAsHtml(response.data);
                    }, function errorCallback(response) {
                        alert("Ошибки работы сервиса");
                    });

            });
    };
    ////////////////////////////////////////////////////////////////////
    $scope.upload = function (id) {
        $http.get("/bookDetails/uploadBookForm/view/" + id).then(function successCallback(response) {
            angular.element(document.querySelector('.bookBlock')).empty();
            $scope.html = $sce.trustAsHtml(response.data);
        }, function errorCallback(response) {
            alert("Ошибки работы сервиса");
        });
    }
    ///////////////////////////////////////////////////////////////////////
    $scope.uploadImage = function (id) {
        $http.get("/bookDetails/uploadImageBookForm/view/" + id).then(function successCallback(response) {
            angular.element(document.querySelector('.bookBlock')).empty();
            $scope.html = $sce.trustAsHtml(response.data);
        }, function errorCallback(response) {
            alert("Ошибки работы сервиса");
        });
    }

//////////////////////////////////////////////////////////////////////////////////
    $scope.ifExist = function (book) {
        for (var i = 0; i < $scope.listBooks.length; i++) {
            if ($scope.listBooks[i].id === book.id || $scope.listBooks[i].issn === book.issn) {
                return true;
            }
        }
        return false;
    }
    $scope.imageName = function (book) {
        if (book.imageName === null)
            return "/resources/img/book.png";
        else return "/resources/Library/Book_Images/" + book.imageName;
    }
    $scope.showFilter = function () {
        $scope.signed = false;
        $scope.issn = undefined;
        $scope.publicationPlace = undefined;
        $scope.name = undefined;
        $scope.publishHouse = undefined;
        $scope.publishStartYear = undefined;
        $scope.publishEndYear = undefined;
        $http.get("/filter").then(function successCallback(response) {
            angular.element(document.querySelector('.filter')).empty();
            angular.element(document.querySelector('.bookBlock')).empty();
            $scope.html3 = $sce.trustAsHtml(response.data);
        }, function errorCallback(response) {
            alert("Ошибки работы сервиса");
        });
    };
    $scope.signed = false;
    $scope.issn = undefined;
    $scope.publicationPlace = undefined;
    $scope.name = undefined;
    $scope.publishHouse = undefined;
    $scope.publishStartYear = undefined;
    $scope.publishEndYear = undefined;
    $scope.changeCheckbox = function () {
        $scope.signed = !$scope.signed;
    };

    $scope.filterStart = function () {
        $http.get("/filter/start/", {
            params: {
                "issn": $scope.issn,
                "publicationPlace": $scope.publicationPlace,
                "name": $scope.name,
                "publishHouse": $scope.publishHouse,
                "publishStartYear": $scope.publishStartYear,
                "publishEndYear": $scope.publishEndYear,
                "signed": $scope.signed
            }
        }).then(function successCallback(response) {
            $scope.listBooks = response.data;
            if ($scope.listBooks.length === 0)
                $http.get("/booksList/booksListEmpty").then(function successCallback(response) {
                    angular.element(document.querySelector('.answerMessages')).empty();
                    $scope.html2 = $sce.trustAsHtml(response.data);
                }, function errorCallback(response) {
                    alert("Ошибки работы сервиса");
                });
            else {
                $http.get("/books/view").then(function successCallback(response) {
                    angular.element(document.querySelector('.bookBlock')).empty();
                    $scope.html = $sce.trustAsHtml(response.data);
                }, function errorCallback(response) {
                    alert("Ошибки работы сервиса");
                });
            }
        }, function errorCallback(response) {
            if (response.status == 409) {
                $http.get("/book/filterErrorMessage").then(function successCallback(response) {
                    angular.element(document.querySelector('.answerMessages')).empty();
                    $scope.html2 = $sce.trustAsHtml(response.data);
                }, function errorCallback(response) {
                    alert("Ошибки работы сервиса");
                });
            }
            else alert("Ошибки работы сервиса");
        });
    };
}]);
//////////////////////////////////////////////


///////////////////////////////////////////////////
//Seach
function seach() {
    var input, filter, h2, p, i, div;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    div = document.getElementsByClassName("bookPost");
    for (i = 0; i < div.length; i++) {
        p = div[i].getElementsByTagName("p");
        h2 = div[i].getElementsByTagName("h2");
        if (p.innerHTML.toUpperCase().indexOf(filter) > -1 || h2.innerHTML.toUpperCase().indexOf(filter) > -1) {
            div.style.display = "";
        }
        else
            div.style.display = "none";
    }
}
/////////////////////////////////////