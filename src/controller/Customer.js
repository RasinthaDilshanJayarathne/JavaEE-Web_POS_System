/*
$("#placeOrder").click(function () {
    genarateCustomerId();

});

function genarateCustomerId(){
    $("#txtPopCustId").val("C00-001");
    var customerId = customerDB[customerDB.length - 1].getCustomerId();
    var tempId = parseInt(customerId.split("-")[1]);
    tempId = tempId + 1;
    if (tempId <= 9) {
        $("#txtPopCustId").val("C00-00" + tempId);
    } else if (tempId <= 99) {
        $("#txtPopCustId").val("C00-0" + tempId);
    } else {
        $("#txtPopCustId").val("C00-0" + tempId);
    }
}

function loadAllCustomer(){
    $("#customerTable").empty();
    $.ajax({
        url: "customer",
        method : "GET",
        /!* dataType :"json",*!/
        success: function (resp) {
            for (var i in resp) {
                var id = resp[i].id;
                var name = resp[i].name;
                var address = resp[i].address;
                var contact = resp[i].contact;

                var row = `<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${contact}</td></tr>`;
                $("#customerTable").append(row);
            }
        }
    });
}

$("#btnLoadAll").click(function (){
    loadAllCustomer();
});

$("#popCustBtnAdd").click(function (){
    let data = $("#customerForm").serialize();
    console.log(data)
    $.ajax({
        url:"customer",
        method: "POST",
        data:data,
        success:function (resp){
            alert(resp)
            loadAllCustomer();
        },
        error: function (ob, textStatus, error) {
            alert(textStatus);
            console.log(ob.responseText);
        }
    });
});*/
