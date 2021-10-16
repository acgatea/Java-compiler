MAVEN = mvn
APPNAME = joosc
NASM = nasm
APPPATH = "target/appassembler/bin"

.PHONY: clean

all: clean build

build:
	chmod +x $(NASM)
	$(MAVEN) package appassembler:assemble
	ln -s $(APPPATH)/$(APPNAME) -t .

clean:
	$(MAVEN) clean
	-rm $(APPNAME)
	rm -r output

