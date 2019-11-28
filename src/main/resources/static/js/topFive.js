// console.log('TOp-5')
//
// function getTopFive() {
//     // let action = document.getElementById("myRadio").value;
//     // document.getElementById("demo").innerHTML = x;
//
//     var message = $(".myRadio:checked").val();
//     console.log(message)
// }
//
// getTopFive();
//
// // $(".myRadio").click(function() {
// //     $.post(
// //         "user_submit.php",
// //         {vote: $("[name='vote']:checked").val()},
// //         function(data){
// //         });
// // });
// console.log('Afrter-Top-5');
//
// $('#create-destination').on('click', '.myRadio', function (ev) {
//     const url = $(this).attr('action');
//
//     // loader.show();
//     console.log("FUnction")
//     fetch("/api/top-five", {method: 'get'})
//         .then(response => response.json())
//         .then(data => {
//             console.log(data)
//             // loader.hide();
//             let message = $(".myRadio:checked").val();
//             console.log(message)
//         });
//
//     ev.preventDefault();
//     return false;
// });
//
// // $('#items-table').on('submit', '.buy-item-form', function (ev) {
// //     const url = $(this).attr('action');
// //
// //     loader.show();
// //     fetch(url, {method: 'post'})
// //         .then(data => {
// //             console.log(data)
// //             loader.hide();
// //         });
// //
// //     ev.preventDefault();
// //     return false;
// // });