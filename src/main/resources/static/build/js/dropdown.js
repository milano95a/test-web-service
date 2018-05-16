$(document).ready(function(){

    // $('#subject').change(function () {
    //     var subject = $('#subject').val();
    //
    //     var topicDropDown = document.getElementById("topic");
    //
    //     while (topicDropDown.firstChild) {
    //         topicDropDown.removeChild(topicDropDown.firstChild);
    //     }
    //
    //     $.ajax({
    //         url:'/admin/gettopics/' + subject,
    //         type:"GET",
    //
    //         beforeSend:function () {
    //             console.log('before send');
    //         },
    //
    //         success:function (result, status, xhr) {
    //             console.log('success');
    //
    //             var topicDropDown = document.getElementById("topic");
    //
    //             var option = document.createElement('option');
    //             option.text = 'Select Topic';
    //             option.value = '';
    //             topicDropDown.appendChild(option);
    //
    //             var language = document.getElementById('language');
    //
    //             if(language[language.selectedIndex].text.toLowerCase().includes('rus')){
    //                 console.log("-------------------------------------true");
    //                 for(i in result){
    //                     console.log(result);
    //                     var option = document.createElement('option');
    //                     option.text = result[i].ruTopic;
    //                     option.value = result[i].ruTopic;
    //                     topicDropDown.appendChild(option);
    //                 }
    //             }else{
    //                 console.log("-------------------------------false");
    //                 for(i in result){
    //                     console.log(result);
    //                     var option = document.createElement('option');
    //                     option.text = result[i].topic;
    //                     option.value = result[i].topic;
    //                     topicDropDown.appendChild(option);
    //                 }
    //             }
    //         },
    //
    //         error:function (xhr,status,error) {
    //             console.log('error');
    //             console.log('xhr ' + xhr);
    //             console.log('status ' + status);
    //             console.log('error ' + error);
    //         }
    //     })
    //
    // });

    $('#subject').change(function () {
        var subject = $('#subject').val();

        var topicDropDown = document.getElementById("topic");

        while (topicDropDown.firstChild) {
            topicDropDown.removeChild(topicDropDown.firstChild);
        }

        $.ajax({
            url:'/admin/gettopics/' + subject,
            type:'GET',

            success:function (result, status, xhr) {

                var option = document.createElement('option');
                option.text = 'Select Topic';
                option.value = '';
                topicDropDown.appendChild(option);

                var language = document.getElementById('language');

                if(language[language.selectedIndex].text.toLowerCase().includes('rus')){
                    for(i in result){
                        var option = document.createElement('option');
                        option.text = result[i].ruTopic;
                        option.value = result[i].ruTopic;
                        topicDropDown.appendChild(option);
                    }
                }else if(language[language.selectedIndex].text.toLowerCase().includes('uz')){
                    for(i in result){
                        var option = document.createElement('option');
                        option.text = result[i].topic;
                        option.value = result[i].topic;
                        topicDropDown.appendChild(option);
                    }
                }
            },

            error:function (xhr,status,error) {
                console.log('status ' + status);
                console.log('error ' + error);
            }
        })

    });

    $('#language').change(function () {

        var subjectDropDown = document.getElementById('subject');
        while(subjectDropDown.firstChild){
            subjectDropDown.removeChild(subjectDropDown.firstChild);
        }
        var subjectOption = document.createElement('option');
        subjectOption.text = 'Select Subject';
        subjectOption.value = '';
        subjectDropDown.appendChild(subjectOption);

        var topicDropDown = document.getElementById('topic');
        while (topicDropDown.firstChild) {
            topicDropDown.removeChild(topicDropDown.firstChild);
        }
        var topicOption = document.createElement('option');
        topicOption.text = 'Select Topic';
        topicOption.value = '';
        topicDropDown.appendChild(topicOption);


        var languageValue = $('#language').val();

        $.ajax({
            url:'/admin/getsubjects',
            type:'GET',

            success:function (result, status, xhr) {

                var language = document.getElementById('language');
                console.log(result);

                if(language[language.selectedIndex].text.toLowerCase().includes('rus')){
                    for(i in result){
                        var option = document.createElement('option');
                        option.text = result[i].ruSubject;
                        option.value = result[i].ruSubject;
                        subjectDropDown.appendChild(option);
                    }
                }else if(language[language.selectedIndex].text.toLowerCase().includes('uz')){
                    for(i in result){
                        var option = document.createElement('option');
                        option.text = result[i].subject;
                        option.value = result[i].subject;
                        subjectDropDown.appendChild(option);
                    }
                }
            },

            error:function (xhr,status,error) {
                console.log('status ' + status);
                console.log('error ' + error);
            }
        })

    });

    // $('#filterSubject').change(function () {
    //     var subject = $('#filterSubject').val();
    //
    //     var topicDropDown = document.getElementById("topic");
    //     while (topicDropDown.firstChild) {
    //         topicDropDown.removeChild(topicDropDown.firstChild);
    //     }
    //
    //     $.ajax({
    //         url:'/admin/gettopics/' + subject,
    //         type:"GET",
    //
    //         beforeSend:function () {
    //             console.log('before send');
    //         },
    //
    //         success:function (result, status, xhr) {
    //             console.log('success');
    //             console.log('xhr ' + xhr);
    //             console.log('status ' + status);
    //             console.log('result ' + JSON.stringify(result));
    //
    //             var topicDropDown = document.getElementById("topic");
    //
    //             var option = document.createElement('option');
    //             option.text = 'All';
    //             option.value = '';
    //             topicDropDown.appendChild(option);
    //
    //             for(i in result){
    //                 console.log(result);
    //                 var option = document.createElement('option');
    //                 option.text = result[i].topic;
    //                 option.value = result[i].topic;
    //                 topicDropDown.appendChild(option);
    //             }
    //         },
    //
    //         error:function (xhr,status,error) {
    //             console.log('error');
    //             console.log('xhr ' + xhr);
    //             console.log('status ' + status);
    //             console.log('error ' + error);
    //         }
    //     })
    //
    // });
});
