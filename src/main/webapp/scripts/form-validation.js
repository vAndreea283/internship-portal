// Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) { // verifica daca toate campurile "required" sunt completate
                event.preventDefault() // opreste submit-ul (browser-ul l-ar fi oprit oricum, dar acum controlam noi)
                event.stopPropagation()
            }
            
            form.classList.add('was-validated') // adauga clasa care activeaza stilurile Bootstrap
        }, false)
    })
})()