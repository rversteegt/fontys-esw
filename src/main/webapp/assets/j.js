$(function() {
  $("#addComment").submit(function(e){
      e.preventDefault();

      var $message = $(this).find("textarea[name='message']");
      var $id = $(this).find("input[name='id']");
      var $responses = $("#responses");

      $.ajax(
          {
              url : '/app/api/blog/addCommentToPosting/' + $id.val(),
              method : 'POST',
              data : {
                  message : $message.val()
              },
              error : function(error) {
              },
              success : function(response) {
                  var comments = response.posting.comments;

                  $responses.html('');
                  $message.val('');

                  $.each(comments, function(k, comment) {
                      $responses.append('<i>' + moment(comment.timestamp).format('D-M-YYYY') + '</i><p>' + comment.message + '</p>');
                  });
              },
              complete : function() {
              },
          }
      );
  });
});