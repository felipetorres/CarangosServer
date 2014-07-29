package br.com.caelum.carangos.controllers;

import java.util.List;

import br.com.caelum.carangos.modelo.BlogPost;
import br.com.caelum.carangos.repositories.BlogPostRepository;
import br.com.caelum.carangos.view.Pagina;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;

@Resource
public class BlogpostController {

	private final Result result;
	private final BlogPostRepository repository;
	
	private final Validator validator;
	
	public BlogpostController(Result result, BlogPostRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}  
	
	
	@Post("/post/image")
	public void uploadImage(UploadedFile file, BlogPost post) {
		BlogPost blogPost = repository.find(post.getId());
		
		blogPost.setFoto(file.getFileName());
		
		repository.update(blogPost);
		
		result.use(Results.json()).from(file.getFileName()).serialize();
	}
	
	@Get("/posts")
	public List<BlogPost> index() {
		return repository.findAll();
	}
	
	@Get("/post/list")
	public void buscaPaginada(Pagina pagina){
		if (pagina==null) {
			pagina = new Pagina();
		} 
		List<BlogPost> posts = repository.find(pagina);
//			result.use(Results.representation()).from(posts).serialize();
		result.use(Results.json()).from(posts).recursive().serialize();
	}
	
	@Post("/posts")
	public void create(BlogPost post) {
		validator.validate(post);
		validator.onErrorUsePageOf(this).newBlogpost();
		repository.create(post);
		result.redirectTo(this).index();
	}
	
	@Post("/post/salvar")
	public void createPost(BlogPost post) {
		BlogPost novoPost = repository.create(post);
		
		result.use(Results.json()).from(novoPost).recursive().serialize();
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Get("/posts/new")
	public BlogPost newBlogpost() {
		return new BlogPost();
	}
	
	@Put("/posts")
	public void update(BlogPost post) {
		validator.validate(post);
		validator.onErrorUsePageOf(this).edit(post);
		repository.update(post);
		result.redirectTo(this).index();
	}
	
	@Get("/posts/{post.id}/edit")
	public BlogPost edit(BlogPost post) {
		
		return repository.find(post.getId());
	}

	@Get("/posts/{post.id}")
	public BlogPost show(BlogPost post) {
		return repository.find(post.getId());
	}

	@Delete("/posts/{post.id}")
	public void destroy(BlogPost post) {
		repository.destroy(repository.find(post.getId()));
		result.redirectTo(this).index();  
	}
}