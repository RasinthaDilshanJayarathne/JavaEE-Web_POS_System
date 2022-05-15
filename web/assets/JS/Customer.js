$("#customer").click(function (){
    loadAllCustomer();
})

$("#popCustBtnAdd").click(function () {
    let data = $("#customerForm").serialize();
    console.log(data)
    $.ajax({
        url: "customer",
        method: "POST",
        data: data,
        success:function (res){
            if (res.status==200){
                alert(res.message);
                loadAllCustomer();
            }else {
                alert(res.data);
            }
        },
        error: function (ob, textStatus, error) {
            alert(textStatus);
        }
    });
});

$("#btnCustomerDelete").click(function (){
    let custId = $("#txtCustId").val();
    $.ajax({
        url:"customer?CustId="+custId,
        method:"DELETE",
        //data :data,
        success: function (res) {
            console.log(res);
            if (res.status == 200) {
                alert(res.message);
                loadAllCustomers();
            } else if (res.status == 400) {
                alert(res.data);
            } else {
                alert(res.data);
            }
        },
        error: function (ob, status, t) {
            console.log(ob);
            console.log(status);
            console.log(t);
        }
    })

});

function loadAllCustomer() {
    $("#customerTable").empty();
    $.ajax({
        url: "customer",
        method: "GET",
        /* dataType :"json",*/
        success: function (resp) {
            for (const customer of resp.data) {
                var row = `<tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.contact}</td></tr>`;
                $("#customerTable").append(row);
            }
            bindClickEvent();
        }
    });
}

function bindClickEvent(){
    $("#customerTable>tr").click(function (){
        let id=$(this).children().eq(0).text();
        let name=$(this).children().eq(1).text();
        let address=$(this).children().eq(2).text();
        let contact=$(this).children().eq(3).text();

        $("#txtCustId").val(id);
        $("#txtCustName").val(name);
        $("#txtCustAddress").val(address);
        $("#txtCustPhoneNo").val(contact);

    });
}

$("#btnCustUpdate").click(function (){
    let fromData = $("#viewCustomerForm").serialize();
    console.log(fromData);
    $.ajax({
        url:"customer?"+fromData,
        method:"PUT",
        //data: fromData,
        success:function (res){
            console.log(res)
            loadAllCustomer();
        }
    })
});

$("#btnCustSearch").keyup(function (event) {
    let input = $("#searchBar3").val();
    console.log(input);

    $.ajax({
        url:"customer?SearchId="+input,
        method:"GET",
        //data :data,

        success : function (resp){
            console.log(resp);
            loadAllCustomer();
        }
    })

});