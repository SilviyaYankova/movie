const allDramas = [];
const allMovies = [];

fetch("http://localhost:8080/dramas/drama-api").then(response => response.json()).then(data => {
    for (let drama of data) {
        allDramas.push(drama);
    }
})
fetch("http://localhost:8080/dramas/movie-api").then(response => response.json()).then(data => {
    for (let movie of data) {
        allMovies.push(movie);
    }
})

console.log(allDramas)
console.log(allMovies)

window.onload = (event) => {
    console.log('page is fully loaded');
    const searchButton = document.getElementById('search-button');
    const searchInput = document.getElementById('search-input');


    let toList = document.getElementById('listSearch');

    searchButton.addEventListener('click', () => {
        const inputValue = searchInput.value.toLowerCase();
        let filteredDramas = allDramas.filter(drama => drama.title.toLowerCase().includes(inputValue));
        let filteredMovies = allMovies.filter(movie => movie.title.toLowerCase().includes(inputValue));

        let all = [];
        if (filteredDramas.length >= 1) {
            all.push(filteredDramas);
        }

        if (filteredMovies.length >= 1) {
            all.push(filteredMovies);
        }


        console.log(all)
        displayFoundDramas(all);
        // displayFoundDramas(filteredDramas);
        // displayFoundMovies(filteredMovies);
    });


    const displayFoundDramas = (entity) => {
        entity.forEach(entity => {
            toList.innerHTML = entity
                .map((d) => {
                    console.log(d.imageURL)
                    return `<div class="col-md-3">
                    <div class=" card mb-4 box-shadow">
                          <img src="${d.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 300px; width: 100%; display: block;">
                        <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom">Title: ${d.title}</p>
                        <p class="card-text border-bottom">Country: ${d.country}</p>
                        <p class="card-text border-bottom">Episodes: ${d.episodes}</p>
                        <p class="card-text border-bottom">Release Date: ${d.releaseDate}</p>
                        <p class="card-text border-bottom">Director: ${d.director}</p>
                    </div>
                    <br>
                    <div class="d-flex justify-content-between align-items-center text-dark">
                        <div class="btn-group">
                            <a href="" type="button" class="btn btn-sm btn-info">Details</a>
                        </div>
                        <div class="btn-group">
                            <a href="" type="button" class="btn btn-sm btn-info">Delete</a>
                        </div>
                    </div>
                </div>
                    </div>
                </div>`
                }).join('');
        })





    }

};
// const displayFoundMovies = (drama) => {
//
//     toList.innerHTML = drama
//         .map((d) => {
//             console.log(d.imageURL)
//             return `<div class="col-md-3">
//                 <div class=" card mb-4 box-shadow">
//                       <img src="${d.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
//                  data-holder-rendered="true"
//                  style="height: 300px; width: 100%; display: block;">
//                     <div class="card-body">
//                 <div class="text-center">
//                     <p class="card-text border-bottom">Title: ${d.title}</p>
//                     <p class="card-text border-bottom">Country: ${d.country}</p>
//                     <p class="card-text border-bottom">Episodes: ${d.episodes}</p>
//                     <p class="card-text border-bottom">Release Date: ${d.releaseDate}</p>
//                     <p class="card-text border-bottom">Director: ${d.director}</p>
//                 </div>
//                 <br>
//                 <div class="d-flex justify-content-between align-items-center text-dark">
//                     <div class="btn-group">
//                         <a href="" type="button" class="btn btn-sm btn-info">Details</a>
//                     </div>
//                     <div class="btn-group">
//                         <a href="" type="button" class="btn btn-sm btn-info">Delete</a>
//                     </div>
//                 </div>
//             </div>
//                 </div>
//             </div>`
//         }).join('');
//
// }

// const displayDramasAndMoviesBySearch = (drama) => {
//     console.log(drama)
//     toList.innerHTML = drama
//         .map((d) => {
//             return `<div class="col-md-3">
//             <div class=" card mb-4 box-shadow">
//                 <img src="${d.imageURL}" class="card-img-top" alt="Thumbnail [100%x225]"
//                      data-holder-rendered="true"
//                      style="height: 300px; width: 100%; display: block;">
//                 <div class="card-body">
//                     <div class="text-center">
//                         <p class="card-text border-bottom" "${d.title}"></p>
//                         <p class="card-text border-bottom" "${d.description}"></p>
//                         <p class="card-text border-bottom"></p>
//                         <p class="card-text border-bottom"></p>
//                         <p class="card-text border-bottom"></p>
//                         <p class="card-text border-bottom">
//                         <p>
//                     </div>
//                     <br>
//                     <div class="d-flex justify-content-between align-items-center text-dark">
//                         <div class="btn-group">
//                             <a href="" type="button" class="btn btn-sm btn-info">Details</a>
//                         </div>
//                         <div class="btn-group">
//                             <a href="" type="button" class="btn btn-sm btn-info">Delete</a>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>`
//         })
//         .join('');
//
// }


