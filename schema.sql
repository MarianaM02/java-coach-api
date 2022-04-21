create table capitulo (
       id bigint not null auto_increment,
        nombre varchar(255),
        numero integer,
        nivel_id bigint,
        primary key (id)
    ) engine=InnoDB;
    
    create table concepto (
       id bigint not null auto_increment,
        contenido TEXT,
        nombre varchar(255),
        capitulo_id bigint,
        primary key (id)
    ) engine=InnoDB;
    
    create table ejemplo (
       id bigint not null auto_increment,
        descripcion varchar(255),
        ejemplo varchar(255),
        concepto_id bigint,
        primary key (id)
    ) engine=InnoDB;
    
    create table nivel (
       id bigint not null auto_increment,
        nombre varchar(255),
        primary key (id)
    ) engine=InnoDB;
    
    create table pregunta (
       id bigint not null auto_increment,
        pregunta varchar(255),
        concepto_id bigint,
        primary key (id)
    ) engine=InnoDB;
    
    create table pregunta_respuestas (
       pregunta_id bigint not null,
        respuestas_id bigint not null
    ) engine=InnoDB;
    
    create table respuesta (
       id bigint not null auto_increment,
        respuesta varchar(255),
        valida bit,
        primary key (id)
    ) engine=InnoDB;
    
    alter table pregunta_respuestas 
       drop index UK_fpufmfdngc86apu62gj1g6a7h;
    alter table pregunta_respuestas 
       add constraint UK_fpufmfdngc86apu62gj1g6a7h unique (respuestas_id);
    alter table capitulo 
       add constraint FKg82ulgutm5jjesplyelpc1w68 
       foreign key (nivel_id) 
       references nivel (id);
    alter table concepto 
       add constraint FKarxkebpf59unsdj3efxam7sgc 
       foreign key (capitulo_id) 
       references capitulo (id);
    alter table ejemplo 
       add constraint FKhw9nl5fm428q704nx3p9o0rfo 
       foreign key (concepto_id) 
       references concepto (id);
    alter table pregunta 
       add constraint FK4i17pwic7lp6tha9bkbtw3itm 
       foreign key (concepto_id) 
       references concepto (id);
    alter table pregunta_respuestas 
       add constraint FKdfm1p6a62pn0d5fepdmve24ko 
       foreign key (respuestas_id) 
       references respuesta (id);
    alter table pregunta_respuestas 
       add constraint FKs2mpjwo55glpvg9g2rqt99r0u 
       foreign key (pregunta_id) 
       references pregunta (id);