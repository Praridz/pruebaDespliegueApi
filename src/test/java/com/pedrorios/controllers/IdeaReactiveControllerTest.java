package com.pedrorios.controllers;

class IdeaReactiveControllerTest {

    /*

    WebTestClient webTestClient;
    IdeaRepository ideaRepository;
    IdeaController ideaController;


    @BeforeEach
    public void setUp() {
        ideaRepository = Mockito.mock(IdeaRepository.class);
        ideaController = new IdeaController(ideaRepository);
        webTestClient = WebTestClient.bindToController(ideaController).build();
    }
    @Test
    public void obtenerIdeas() {
        BDDMockito.given(ideaRepository.findAll())
                .willReturn(Flux.just(
                        Idea.builder().id(1).titulo("fisicapp udea").descripcion("Aplicación móvil colaborativo que permite estudiantes  proponer ejercicios de física  y mediante la comunidad interesada,  obtener repuestas a dichos ejercicios, las cuales pueden ser verificadas por varios usuarios").num_equipos(1).max_miembros(0).build(),
                        Idea.builder().id(2).titulo("Identificación de macroinvertebrados utilizando procesado de imágenes y aprendizaje de máquinas").descripcion("descripcion\":\"En la actualidad, un grupo de PI - I a desarrollado un aplicativo para la identificación de macroinvetebrados a partir de una imagen obtenida mediante microscopio utilizando LBP y K-means para la clasificación . El Presente proyecto espera explorar otras técnicas de extracción de características como HOG, SURF, HAAR, etc  y de clasificación como SVM, Decision Trees, NN (C,R) etc para la clasificación. Se cuenta con la base de datos de las imágenes para entrenamiento y verificación. La idea final es hacer un aplicativo que pueda ser usado en campo para los ambientalistas.").num_equipos(1).max_miembros(0).build()
                ));

        webTestClient.get().uri("/idea")
                .exchange()
                .expectBodyList(Idea.class)
                .hasSize(2);
    }

    @Test
    public void obtenerIdeaPorId() {
        BDDMockito.given(ideaRepository.findById(1))
                .willReturn(Mono.just(
                        Idea.builder().id(1).titulo("fisicapp udea").descripcion("Aplicación móvil colaborativo que permite estudiantes  proponer ejercicios de física  y mediante la comunidad interesada,  obtener repuestas a dichos ejercicios, las cuales pueden ser verificadas por varios usuarios").num_equipos(1).max_miembros(0).build()
                ));

        webTestClient.get().uri("/idea/1")
                .exchange()
                .expectBody(Idea.class);
    }

    @Test
    public void crearIdea() {
        BDDMockito.given(ideaRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Idea.builder().build()));

        Mono<Idea> ideaACrear = Mono.just(Idea.builder().id(200).build());

        webTestClient.post().uri("/idea")
                .body(ideaACrear, Idea.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void actualizarIdea() {
        BDDMockito.given(ideaRepository.save(any(Idea.class)))
                .willReturn(Mono.just(Idea.builder().build()));

        Mono<Idea> ideaAActualizar = Mono.just(Idea.builder().id(100).titulo("Idea de prueba").build());

        webTestClient.put().uri("/idea/200")
                .body(ideaAActualizar, Idea.class)
                .exchange()
                .expectStatus().isOk();
    }

     */

}